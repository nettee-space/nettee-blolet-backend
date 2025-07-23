package nettee.blolet.blog.application.service

import io.kotest.assertions.throwables.*
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.*
import io.kotest.matchers.equals.*
import io.mockk.*
import nettee.blolet.blog.application.port.BlogCommandRepositoryPort
import nettee.blolet.blog.domain.Blog
import nettee.blolet.blog.exception.BlogErrorCode.BLOG_MAXIMUM_EXCEEDED
import nettee.common.CustomException
import java.time.Instant

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
            .name("A Blog")
            .url("")
            .createdAt(now)
            .updatedAt(now)
            .build()
        val expectedItem = Blog.builder()
            .id(item.id ?: "1")
            .userId(item.userId)
            .name(item.name)
            .url(item.url)
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
                    .url(inputtedBlog.url)
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
                    .url(inputtedBlog.url)
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
})