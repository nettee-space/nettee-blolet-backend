package nettee.jwt

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.jsonwebtoken.Claims
import java.util.*

class JwtParserTest : FreeSpec({

    val keyType = "HMAC"
    val secretKey = Base64.getEncoder().encodeToString("nettee-blolet-jwt-secret-key-extend".toByteArray())
    val issuer = JwtIssuer(
        keyType,
        secretKey,
        null,
        60,
    )
    val parser = JwtParser(
        keyType,
        secretKey,
        null
    )

    val token = issuer.issueAccessToken("sun", mapOf("role" to "USER"))

    "생성한 JWT를 파싱하여 클레임을 비교한다" {
        val claims: Claims = shouldNotThrowAny {
            parser.parseClaims(token)
        }

        claims.subject shouldBe "sun"
        claims["role"] shouldBe "USER"
    }
})
