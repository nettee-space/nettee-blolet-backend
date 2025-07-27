package nettee.jwt

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.string.shouldNotBeBlank
import java.security.KeyPairGenerator
import java.util.*

class JwtIssuerTest : FreeSpec({

    "HMAC 알고리즘을 사용한 액세스 토큰을 생성할 수 있다" {
        // HMAC 비밀 키 생성
        val keyType = "HMAC"
        val secretKey = Base64.getEncoder()
            .encodeToString("nettee-blolet-jwt-secret-key-extend".toByteArray())

        val issuer = JwtIssuer(
            keyType,
            secretKey,
            null,
            60L
        )

        val token = issuer.issueAccessToken("sun", mapOf("role" to "USER"))
        println("HMAC Access Token = $token")
        token.shouldNotBeBlank()
    }


    "RSA 알고리즘을 사용한 액세스 토큰을 생성할 수 있다" {
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
        keyPairGenerator.initialize(2048)
        val keyPair = keyPairGenerator.generateKeyPair()

        val privateKey = Base64.getEncoder().encodeToString(keyPair.private.encoded)
        val keyType = "RSA"

        val issuer = JwtIssuer(
            keyType,
            null,
            privateKey,
            60L
        )

        val token = issuer.issueAccessToken("sun", mapOf("role" to "USER"))
        println("RSA Access Token = $token")
        token.shouldNotBeBlank()
    }
})
