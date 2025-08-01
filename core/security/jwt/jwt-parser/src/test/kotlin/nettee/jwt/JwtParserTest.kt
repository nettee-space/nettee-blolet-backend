package nettee.jwt

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.jsonwebtoken.Claims
import java.security.KeyPairGenerator
import java.util.*

class JwtParserTest : FreeSpec({

    "HMAC 알고리즘 기반 JWT를 파싱하여 클레임을 비교한다" {
        val keyType = "HMAC"
        val secretKey = Base64.getEncoder()
            .encodeToString("nettee-blolet-jwt-secret-key-extend".toByteArray())

        val issuer = JwtIssuer(
            keyType,
            secretKey,
            null
        )
        val parser = JwtParser(
            keyType,
            secretKey,
            null
        )

        val token = issuer.issue("sun", mapOf("role" to "USER"), 600)

        val claims: Claims = shouldNotThrowAny {
            parser.parseClaims(token)
        }

        claims.subject shouldBe "sun"
        claims["role"] shouldBe "USER"
    }

    "RSA 알고리즘 기반 JWT를 파싱하여 클레임을 비교한다" {
        // RSA 키쌍 생성
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
        keyPairGenerator.initialize(2048)
        val keyPair = keyPairGenerator.generateKeyPair()

        val privateKey = Base64.getEncoder().encodeToString(keyPair.private.encoded)
        val publicKey = Base64.getEncoder().encodeToString(keyPair.public.encoded)
        val keyType = "RSA"

        val issuer = JwtIssuer(
            keyType,
            null,         // secretKey는 null
            privateKey,
        )
        val parser = JwtParser(
            keyType,
            null,         // secretKey는 null
            publicKey
        )

        val token = issuer.issue("sun", mapOf("role" to "USER"), 600)

        val claims: Claims = shouldNotThrowAny {
            parser.parseClaims(token)
        }

        claims.subject shouldBe "sun"
        claims["role"] shouldBe "USER"
    }
})