package nettee.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.SecretKey;

public final class JwtParser {

    private final JwtParserBuilder jwtParserBuilder;

    public JwtParser(String keyType, String secretKey, String publicKey) {
        if (keyType.equalsIgnoreCase("HMAC")) {
            // HMAC 비밀키 생성
            byte[] keyBytes = Decoders.BASE64.decode(secretKey);
            SecretKey signingKey = Keys.hmacShaKeyFor(keyBytes);
            this.jwtParserBuilder = Jwts.parser()
                    .verifyWith(signingKey);
        } else {
            // 공개키 생성
            byte[] keyBytes = Decoders.BASE64.decode(publicKey);
            try {
                KeyFactory keyFactory = KeyFactory.getInstance(keyType);
                PublicKey signingKey = keyFactory.generatePublic(new X509EncodedKeySpec(keyBytes));
                this.jwtParserBuilder = Jwts.parser()
                        .verifyWith(signingKey);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * JWT 토큰을 파싱하여 Claims을 반환한다.
     * 이 과정에서 서명 검증, 만료 시간 체크가 수행됩니다.
     */
    public Claims parseClaims(String token) throws JwtException {
        return jwtParserBuilder
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}