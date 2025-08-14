package nettee.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeBlank
import nettee.jwt.issuer.JwtIssuer
import java.security.KeyPairGenerator
import java.security.SecureRandom
import java.util.*

class JwtIssuerTest : FreeSpec({

    "512bit HMAC 키로 jwt를 생성하고, HS512 알고리즘이 자동 추론되어 적용되었는지 검증한다" {
        // given
        val secureRandom = SecureRandom()
        val keyBytes = ByteArray(64) // 64byte(=512bit) 배열 생성
        secureRandom.nextBytes(keyBytes)  // keyBytes 배열을 랜덤 바이트로 채움
        val secretKey = Base64.getEncoder().encodeToString(keyBytes)

        val issuer = JwtIssuer(
            "HMAC",
            secretKey,
            null
        )

        // when
        val token = issuer.issue("sun", mapOf("role" to "USER"), 600)
        token.shouldNotBeBlank()

        // then
        // 1. 검증 키 생성
        val verificationKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey))

        // 2. jwt 파싱
        val parsedClaims = Jwts.parser()
            .verifyWith(verificationKey)
            .build()
            .parseSignedClaims(token)

        // 3. subject, role 검증
        parsedClaims.payload.subject shouldBe "sun"
        parsedClaims.payload["role"] shouldBe "USER"

        // 4. HMAC 512bit 키에 대해 RS512 알고리즘으로 자동 추론하여 적용하는지 검증
        val algorithm = parsedClaims.header.algorithm
        algorithm shouldBe "HS512"
    }

    "4096bit RSA 키로 jwt를 생성하고, RS512 알고리즘이 자동 추론되어 적용되었는지 검증한다" {
        // given
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
        keyPairGenerator.initialize(4096)           // 4096bit 키 생성
        val keyPair = keyPairGenerator.generateKeyPair()    // 공개키, 개인키 쌍 생성
        val privateKey = Base64.getEncoder().encodeToString(keyPair.private.encoded)

        val issuer = JwtIssuer(
            "RSA",
            null,
            privateKey
        )

        // when
        val token = issuer.issue("sun", mapOf("role" to "USER"), 600)
        token.shouldNotBeBlank()

        // then
        // 1. 검증에는 공개키 사용
        val verificationKey = keyPair.public

        // 2. jwt 파싱
        val parsedClaims = Jwts.parser()
            .verifyWith(verificationKey)
            .build()
            .parseSignedClaims(token)

        // 3. subject, role 검증
        parsedClaims.payload.subject shouldBe "sun"
        parsedClaims.payload["role"] shouldBe "USER"

        // 4. RSA 512bit 키에 대해 RS512 알고리즘으로 자동 추론하여 적용하는지 검증
        val algorithm = parsedClaims.header.algorithm
        algorithm shouldBe "RS512"
    }
})
