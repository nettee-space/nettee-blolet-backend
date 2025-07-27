package nettee.jwt

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.string.shouldNotBeBlank
import java.util.*

class JwtIssuerTest : FreeSpec({

    val keyType = "HMAC"
    val secretKey = Base64.getEncoder().encodeToString("nettee-blolet-jwt-secret-key-extend".toByteArray())
    val issuer = JwtIssuer(
        keyType,
        secretKey,
        null,               // privateKey (HMAC이므로 null)
        60L,  // accessTokenMaxAgeSeconds
    )

    "HMAC 알고리즘을 사용한 액세스 토큰을 생성할 수 있다" {
        val accessToken = issuer.issueAccessToken("sun", mapOf("role" to "USER"))
        println("Access Token = $accessToken")
        accessToken.shouldNotBeBlank()
    }
})
