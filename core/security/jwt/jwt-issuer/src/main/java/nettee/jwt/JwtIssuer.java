package nettee.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;
import java.util.Map;

public class JwtIssuer {

    private final long accessTokenMaxAgeSeconds;
    private final JwtBuilder jwtBuilder;

    // JwtIusser 생성자
    public JwtIssuer(String keyType, String secretKey, String privateKey, long accessTokenMaxAgeSeconds) {

        Key signingKey;
        if (keyType.equalsIgnoreCase("HMAC")) {
            // HMAC 비밀키 생성
            byte[] keyBytes = Decoders.BASE64.decode(secretKey);
            signingKey = Keys.hmacShaKeyFor(keyBytes);
        } else {
            // 개인키 생성
            byte[] keyBytes = Decoders.BASE64.decode(privateKey);
            try {
                KeyFactory keyFactory = KeyFactory.getInstance(keyType);
                signingKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }
        }

        this.accessTokenMaxAgeSeconds = accessTokenMaxAgeSeconds;
        this.jwtBuilder = Jwts.builder()
                .signWith(signingKey);
    }

    public String issueAccessToken(String subject, Map<String, ?> claims) {
        return issue(subject, claims, accessTokenMaxAgeSeconds);
    }

    public String issue(String subject, Map<String, ?> claims, long maxAgeSeconds) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + maxAgeSeconds * 1000);

        /* ╔══════════════════════════════════════════════════╗
           ║                      HEADER:                     ║
           ║ .signWith(jwtProperties.secretKey(), SIG.HS512)  ║
           ╠══════════════════════════════════════════════════╣
           ║                      PAYLOAD                     ║ */
        JwtBuilder builder = jwtBuilder
                .subject(subject)
                .issuedAt(now)
                .expiration(expiration);

        if (claims != null && !claims.isEmpty()) {
            builder.claims(claims);
        }

        return builder.compact();

        /* ║                     COMPLETE                     ║
           ║            SIGNATURE is auto-generated           ║
           ╚══════════════════════════════════════════════════╝ */
    }
}
