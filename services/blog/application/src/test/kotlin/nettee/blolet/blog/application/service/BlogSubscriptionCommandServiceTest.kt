package nettee.blolet.blog.application.service

import io.kotest.assertions.throwables.*
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.*
import io.mockk.*
import nettee.blolet.blog.application.port.BlogSubscriptionCommandRepositoryPort
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
})