package nettee.blolet.blog.application.service

import io.kotest.assertions.throwables.*
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.*
import io.mockk.*
import nettee.blolet.blog.application.port.BlogSubscriptionCommandRepositoryPort
import nettee.blolet.blog.application.usecase.newsletter.BlogNewsletterSubscriptionUseCase
import nettee.blolet.blog.application.usecase.subscription.BlogSubscriptionUseCase
import nettee.blolet.blog.application.usecase.subscription.BlogUnsubscriptionUseCase
import nettee.blolet.blog.application.usecase.subscription.data.SubscriptionStats
import nettee.blolet.blog.domain.BlogSubscription
import nettee.blolet.blog.exception.BlogErrorCode.*
import nettee.common.CustomException
import java.time.Instant
import java.util.*

class BlogSubscriptionCommandServiceTest : FreeSpec({

    val commandPort = mockk<BlogSubscriptionCommandRepositoryPort>()
    val service: BlogSubscriptionUseCase = BlogSubscriptionCommandService(commandPort)
    val unsubscriptionUseCase: BlogUnsubscriptionUseCase = BlogSubscriptionCommandService(commandPort)
    val newsletterSubscriptionUseCase: BlogNewsletterSubscriptionUseCase = BlogSubscriptionCommandService(commandPort)

    beforeTest {
        clearMocks(commandPort, answers = true, recordedCalls = true)
    }

    "[subscribeBlog] 신규 구독 처리" - {
        val userId = "USER-1"
        val blogId = "BLOG-1"
        val userSubscriptions = 3
        val blogSubscribers = 5

        "✅ 신규 구독이면 SubscriptionCount를 올바르게 반환한다" {
            // given
            every { commandPort.existsByUserIdAndBlogId(userId, blogId) } returns false

            val savedSlot = slot<BlogSubscription>()
            every { commandPort.save(capture(savedSlot)) } answers {
                savedSlot.captured
            }

            every { commandPort.countByUserId(userId) } returns userSubscriptions
            every { commandPort.countByBlogId(blogId) } returns blogSubscribers

            // when
            val result: SubscriptionStats = service.subscribeBlog(userId, blogId)

            // then
            // save에 넘긴 엔티티 속성 검증
            with(savedSlot.captured) {
                userId shouldBe userId
                blogId shouldBe blogId
                emailAllowed shouldBe false
                notificationAllowed shouldBe false
            }

            // 반환된 통계 검증
            result.userSubscriptionCount shouldBe userSubscriptions
            result.blogTotalSubscriberCount shouldBe blogSubscribers

            // 호출 순서 검증
            verifySequence {
                commandPort.existsByUserIdAndBlogId(userId, blogId)
                commandPort.save(any())
                commandPort.countByUserId(userId)
                commandPort.countByBlogId(blogId)
            }
        }

        "🚧 이미 구독된 경우 ALREADY_SUBSCRIBED_BLOG 예외를 던진다" {
            // given
            every { commandPort.existsByUserIdAndBlogId(userId, blogId) } returns true

            // when & then
            val exc = shouldThrow<CustomException> {
                service.subscribeBlog(userId, blogId)
            }
            exc.errorCode shouldBe ALREADY_SUBSCRIBED_BLOG

            // save/count 메서드는 호출되지 않아야 함
            verify { commandPort.existsByUserIdAndBlogId(userId, blogId) }
            verify(inverse = true) {
                commandPort.save(any())
                commandPort.countByUserId(any())
                commandPort.countByBlogId(any())
            }
        }
    }

    "[UNSUBSCRIBE] 블로그 구독 취소 시" - {
        val userId = "user1"
        val blogId = "blog-123"
        val subscriptionId = "sub-1"
        val now = Instant.now()
        val subscription = BlogSubscription.builder()
            .id(subscriptionId)
            .userId(userId)
            .blogId(blogId)
            .emailAllowed(false)
            .notificationAllowed(false)
            .createdAt(now)
            .updatedAt(now)
            .build()

        "✅ 기존 구독이 존재하면 취소 후 최신 구독 통계를 반환한다." {
            // mock: 구독 조회, 삭제, 통계 조회
            every { commandPort.findByUserIdAndBlogId(userId, blogId) } returns Optional.of(subscription)
            every { commandPort.deleteById(subscriptionId) } just runs
            every { commandPort.countByUserId(userId) } returns 2
            every { commandPort.countByBlogId(blogId) } returns 5

            // action
            val stats: SubscriptionStats = unsubscriptionUseCase.unsubscribeBlog(userId, blogId)

            // assert: 반환된 통계 값
            stats.userSubscriptionCount shouldBe 2
            stats.blogTotalSubscriberCount shouldBe 5

            // 호출 순서 검증
            verifySequence {
                commandPort.findByUserIdAndBlogId(userId, blogId)
                commandPort.deleteById(subscriptionId)
                commandPort.countByUserId(userId)
                commandPort.countByBlogId(blogId)
            }
        }

        "🚧 구독이 존재하지 않으면 예외가 발생한다." {
            // mock: 구독 없음
            every { commandPort.findByUserIdAndBlogId(userId, blogId) } returns Optional.empty()

            // action & assert
            val ex = shouldThrow<CustomException> {
                unsubscriptionUseCase.unsubscribeBlog(userId, blogId)
            }
            ex.errorCode shouldBe UNSUBSCRIBED_BLOG

            // 삭제 호출 없어야 함
            verify(inverse = true) { commandPort.deleteById(any()) }
        }
    }

    "[subscribeBlogNewsletter] 뉴스레터 구독 로직" - {
        val userId = "USER-1"
        val blogId = "BLOG-1"
        val now = Instant.now()

        // 이미 블로그 구독 중인 엔티티 (notificationAllowed = false)
        val originalSubscription = BlogSubscription.builder()
            .id("SUB-1")
            .userId(userId)
            .blogId(blogId)
            .emailAllowed(false)
            .notificationAllowed(false)
            .createdAt(now)
            .build()

        // update() 호출 시 캡처용 리스트
        val captured = mutableListOf<BlogSubscription>()

        beforeTest {
            // 기본 성공 플로우 모의 설정
            every { commandPort.findByUserIdAndBlogId(userId, blogId) } returns Optional.of(originalSubscription)
            every { commandPort.update(capture(captured)) } answers { firstArg<BlogSubscription>() }
            every { commandPort.countByUserId(userId) } returns 3
            every { commandPort.countByBlogId(blogId) } returns 7
        }

        "✅ 구독 중인 블로그에 한해 뉴스레터 구독이 가능하다" {
            val stats: SubscriptionStats = newsletterSubscriptionUseCase.subscribeBlogNewsletter(userId, blogId)

            // 결과 검증
            stats.userSubscriptionCount shouldBe 3
            stats.blogTotalSubscriberCount shouldBe 7

            // 실제로 emailAllowed가 true로 변경됐는지 확인
            captured.single().emailAllowed shouldBe true

            // 호출 순서 검증

            // update() 호출 뒤에 countByUserId()가 언젠간 호출됐음을 검증
            verifyOrder { // ≠ verifySequence
                commandPort.update(any())
                commandPort.countByUserId(userId)
            }

            // update() 호출 뒤에 countByBlogId()가 언젠간 호출됐음을 검증
            verifyOrder {
                commandPort.update(any())
                commandPort.countByBlogId(blogId)
            }
        }

        "🚧 구독하지 않은 블로그에선 예외가 발생한다" {
            every { commandPort.findByUserIdAndBlogId(userId, blogId) } returns Optional.empty()

            val exc = shouldThrow<CustomException> {
                newsletterSubscriptionUseCase.subscribeBlogNewsletter(userId, blogId)
            }
            exc.errorCode shouldBe UNSUBSCRIBED_BLOG

            // update는 호출되지 않아야 함
            verify(exactly = 0) { commandPort.update(any()) }
        }

        "🚧 이미 뉴스레터를 구독 중이면 예외가 발생한다" {
            // notificationAllowed = true인 경우
            val already = BlogSubscription.builder()
                .id("SUB-1")
                .userId(userId)
                .blogId(blogId)
                .emailAllowed(false)
                .notificationAllowed(true)
                .createdAt(now)
                .build()

            every { commandPort.findByUserIdAndBlogId(userId, blogId) } returns Optional.of(already)

            val exc = shouldThrow<CustomException> {
                newsletterSubscriptionUseCase.subscribeBlogNewsletter(userId, blogId)
            }
            exc.errorCode shouldBe ALREADY_SUBSCRIBED_BLOG_NEWSLETTER

            verify(exactly = 0) { commandPort.update(any()) }
        }
    }
})