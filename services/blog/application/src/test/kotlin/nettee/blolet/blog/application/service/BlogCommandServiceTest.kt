package nettee.blolet.blog.application.service

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.*
import io.kotest.matchers.equals.*
import io.mockk.*
import nettee.blolet.blog.application.port.BlogCommandPort
import nettee.blolet.blog.domain.Blog
import java.time.Instant

class BlogCommandServiceTest : FreeSpec({
    val commandPort = mockk<BlogCommandPort>()
    val commandService = BlogCommandService(commandPort)

    beforeTest {
        clearMocks(commandPort, answers = true, recordedCalls = true)
    }

    "[CREATE ✅] 정상적인 아이템 등록 시" - {
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

        "반환된 객체가 저장된 Blog와 동일해야 한다. (equals)" {
            // mock:
            every { commandPort.save(item) } returns expectedItem // verify(exactly = 1)와 동일 테스트 블록에.

            // action:
            val result = commandService.save(item)

            // assert:
            result shouldNotBe null
            result shouldBeEqual expectedItem
            // 모킹된 port.save(...) 함수가 1회만 호출되었는지 확인
            verify(exactly = 1) { commandPort.save(item) }
        }
    }
})