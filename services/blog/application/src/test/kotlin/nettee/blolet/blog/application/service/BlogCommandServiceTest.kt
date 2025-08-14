package nettee.blolet.blog.application.service

import io.kotest.assertions.throwables.*
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.equals.*
import io.kotest.matchers.*
import io.mockk.*
import nettee.blolet.blog.application.port.BlogCommandRepositoryPort
import nettee.blolet.blog.domain.Blog
import nettee.blolet.blog.exception.BlogErrorCode.*
import nettee.common.CustomException
import java.time.Instant
import java.util.*

class BlogCommandServiceTest : FreeSpec({
    val commandPort = mockk<BlogCommandRepositoryPort>()
    val commandService = BlogCommandService(commandPort)

    beforeTest {
        clearMocks(commandPort, answers = true, recordedCalls = true)
    }

    "[CREATE] 블로그 등록 시" - {
        val now = Instant.now()
        val item = Blog.builder()
            .id(null)
            .userId("USER-1")
            .profileId("PROFILE-1")
            .username("username")
            .nickname("nickname")
            .name("A Blog")
            .urlIdentifier("")
            .createdAt(now)
            .updatedAt(now)
            .build()
        val expectedItem = Blog.builder()
            .id(item.id ?: "1")
            .userId(item.userId)
            .name(item.name)
            .urlIdentifier(item.urlIdentifier)
            .createdAt(item.createdAt)
            .updatedAt(item.createdAt)
            .build()

        "✅ 반환된 객체가 저장된 Blog와 동일해야 한다. (equals)" {
            // mock:
            val capturedItem = mutableListOf<Blog>()
            every { commandPort.countByUserId(any()) } answers {
                val userIdArg: String = firstArg()
                capturedItem.count {it.userId == userIdArg}
            }
            // verify(exactly = 1)와 동일 테스트 블록에.
            every { commandPort.save(capture(capturedItem)) } answers {
                val inputtedBlog: Blog = firstArg()
                Blog.builder()
                    .id(inputtedBlog.id ?: "1")
                    .userId(inputtedBlog.userId)
                    .name(inputtedBlog.name)
                    .urlIdentifier(inputtedBlog.urlIdentifier)
                    .createdAt(inputtedBlog.createdAt)
                    .updatedAt(inputtedBlog.createdAt)
                    .build()
            }

            // action:
            val savedItem: Blog = commandService.save(item)

            // assert:
            savedItem shouldNotBe null
            savedItem shouldBeEqual expectedItem
            // 모킹된 port.save(...) 함수가 1회만 호출되었는지 확인
            verify(exactly = 1) { commandPort.save(item) }
        }

        "🚧 사용자당 블로그 개설 개수를 초과하면 오류를 반환한다." {
            // mock:
            val capturedItem = mutableListOf<Blog>(item)
            every { commandPort.countByUserId(any()) } answers {
                val userIdArg: String = firstArg()
                capturedItem.count {it.userId == userIdArg}
            }
            every { commandPort.save(capture(capturedItem)) } answers {
                val inputtedBlog: Blog = firstArg()
                Blog.builder()
                    .id(inputtedBlog.id ?: "1")
                    .userId(inputtedBlog.userId)
                    .name(inputtedBlog.name)
                    .urlIdentifier(inputtedBlog.urlIdentifier)
                    .createdAt(inputtedBlog.createdAt)
                    .updatedAt(inputtedBlog.createdAt)
                    .build()
            }

            // action & assert:
            val exception = shouldThrow<CustomException> {
                commandService.save(item)
            }
            exception.errorCode shouldBe BLOG_MAXIMUM_EXCEEDED
        }
    }

    "[UPDATE] 블로그 수정 시" - {
        val targetId = "1"
        val userId = "USER-1"
        val newName = "new blog name"
        val newUrl = "https://user1.blolet.com"

        // mock 데이터 초기화
        val now = Instant.now()
        val original = Blog.builder()
            .id(targetId)
            .userId("USER-1")
            .name("A Blog")
            .urlIdentifier("https://old.blolet.com")
            .createdAt(now)
            .updatedAt(now)
            .build()
        val captured = mutableListOf<Blog>()

        beforeTest {
            clearMocks(commandPort, answers = true, recordedCalls = true)
            // 조회 시 원본 반환
            every { commandPort.findById(targetId) } returns Optional.of(original)
            // 저장 시 capture 후, updatedAt만 갱신된 새 객체 반환
            every { commandPort.update(capture(captured)) } answers {
                val input: Blog = firstArg()
                Blog.builder()
                    .id(input.id!!)
                    .userId(input.userId)
                    .name(input.name)
                    .urlIdentifier(input.urlIdentifier)
                    .createdAt(input.createdAt)
                    .updatedAt(Instant.now())
                    .build()
            }
        }

        "✅ 존재하는 아이템을 수정할 수 있다." {
            // action
            val updatingBlog = Blog.builder()
                .id(targetId)
                .userId(userId)
                .name(newName)
                .urlIdentifier(newUrl)
                .build()
            val updated: Blog = commandService.update(updatingBlog)

            // assert
            updated.id shouldBe targetId
            updated.userId shouldBe original.userId
            updated.name shouldBe newName
            updated.urlIdentifier shouldBe newUrl

            // 호출 순서 확인
            verifyOrder {
                commandPort.findById(targetId)
                commandPort.update(any())
            }
        }

        "🚧 name은 null이어선 안 된다." {
            // action & assert
            val updatingBlog = Blog.builder()
                .id(targetId)
                .userId(userId)
                .name(null)
                .urlIdentifier(newUrl)
                .build()
            val exception = shouldThrow<CustomException> {
                commandService.update(updatingBlog)
            }
            exception.errorCode shouldBe BLOG_NAME_CANNOT_BE_BLANK
            verify(inverse = true) { commandPort.save(any()) }
            // `findById` 메서드는 호출되든 아니든 영향이 없음. (테스트 대상 X)
            //  ❌ verify(inverse = true) { commandPort.findById(targetId) }
        }

        "✅ 입력 URL이 null이면 URL은 수정되지 않는다." {
            // action
            val updatingBlog = Blog.builder()
                .id(targetId)
                .userId(userId)
                .name(newName)
                .urlIdentifier(null)
                .build()
            val updated: Blog = commandService.update(updatingBlog)

            // assert
            updated.name shouldBe newName
            updated.urlIdentifier shouldBe original.urlIdentifier // URL 변경 없음

            verifyOrder {
                commandPort.findById(targetId)
                commandPort.update(any())
            }
        }
    }

    "[UPDATE > URL] 블로그 URL 수정 시" - {
        val targetId = "1"
        val wrongId = "999"
        val oldUrl = "https://old.url"
        val newUrl = "https://new.url"
        val now = Instant.now()
        val userId = "USER-1"
        val originalBlog = Blog.builder()
            .id(targetId)
            .userId(userId)
            .name("A Blog")
            .urlIdentifier(oldUrl)
            .createdAt(now)
            .updatedAt(now)
            .build()

        val capturedItems = mutableListOf<Blog>(originalBlog)

        beforeTest {
            // mock
            every { commandPort.findById(targetId) } returns Optional.of(originalBlog)
            every { commandPort.findById(wrongId) } returns Optional.empty()
            every { commandPort.save(capture(capturedItems)) } answers {
                // TODO 다른 테스트 컨테이너에서도 복붙하여 재사용하려고 저장 기능까지 넣음. (언제든 공통된 관리에서 이탈하도록 복붙이 나아 보임.)
                val input = firstArg<Blog>()
                val savedItem = Blog.builder()
                    .id(input.id ?: (capturedItems.size + 1).toString())
                    .userId(input.userId)
                    .name(input.name)
                    .urlIdentifier(input.urlIdentifier)
                    .createdAt(input.createdAt)
                    .updatedAt(Instant.now())
                    .build()
                capturedItems.add(savedItem)
                savedItem
            }
        }

        "✅ 존재하는 블로그의 URL이 정상적으로 업데이트되어 저장된다" {

            // action
            val updated = commandService.updateUrl(userId, targetId, newUrl)

            // assert: URL이 바뀌었는지, 반환 객체가 save 호출된 엔티티와 동일한지
            updated.urlIdentifier shouldBeEqual newUrl
            verify(exactly = 1) { commandPort.findById(targetId) }
            verify(exactly = 1) { commandPort.save(match { it.urlIdentifier == newUrl }) }
            verifySequence {
                commandPort.findById(targetId)
                commandPort.save(any())
            }
        }

        "🚧 blogId가 null이면 NPE를 던진다" {
            shouldThrow<NullPointerException> {
                commandService.updateUrl(userId, null, "https://any.url")
            }

            verify(inverse = true) { commandPort.save(any()) }
        }

        "🚧 존재하지 않는 블로그 ID로 조회 시 BLOG_NOT_FOUND 예외를 던진다" {

            val ex = shouldThrow<CustomException> {
                commandService.updateUrl(userId, wrongId, "https://any.url")
            }
            ex.errorCode shouldBeEqual BLOG_NOT_FOUND

            verify(exactly = 1) { commandPort.findById(wrongId) }
            verify(inverse = true) { commandPort.save(any()) }
        }

        "🚧 url이 null이면 NPE를 던진다" {
            every { commandPort.findById(targetId) } returns Optional.of(originalBlog)

            shouldThrow<NullPointerException> {
                commandService.updateUrl(userId, targetId, null)
            }

            verify(inverse = true) { commandPort.save(any()) }
        }
    }

    "[DELETE] 블로그 삭제 시" - {
        val targetId = "1"
        val wrongTargetId = "999"

        // mock:
        val now = Instant.now()
        val capturedItems = mutableListOf<Blog>(Blog.builder()
            .id("1")
            .userId("USER-1")
            .name("A Blog")
            .urlIdentifier("")
            .createdAt(now)
            .updatedAt(now)
            .build()
        )
        val firstSize = capturedItems.size

        "✅ 존재하는 아이템은 올바르게 삭제된다." {
            every { commandPort.deleteById(any()) } answers {
                val inputtedId: String = firstArg()
                val exists = capturedItems.map { it.id }.contains(inputtedId)
                if (!exists) {
                    throw BLOG_NOT_FOUND.exception()
                }

                capturedItems.removeIf { it.id == inputtedId }
            }

            // action
            commandService.deleteById(targetId)

            // assert
            capturedItems.size shouldBe firstSize - 1
            verify(exactly = 1) { commandPort.deleteById(targetId) }
        }

        "🚧 존재하지 않는 아이템을 삭제하려고 하면 예외를 반환한다." {
            every { commandPort.deleteById(any()) } answers {
                val inputtedId: String = firstArg()
                val exists = capturedItems.map { it.id }.contains(inputtedId)
                if (!exists) {
                    throw BLOG_NOT_FOUND.exception()
                }

                capturedItems.removeIf { it.id == inputtedId }
            }

            // action & assert
            val exception = shouldThrow<CustomException> {
                commandService.deleteById(wrongTargetId)
            }
            exception.errorCode shouldBe BLOG_NOT_FOUND

            // verify: port.deleteById 메서드는 실제로 호출되지 않았는지 확인.
            verify(inverse = true) { commandPort.deleteById(targetId) }
        }
    }
})