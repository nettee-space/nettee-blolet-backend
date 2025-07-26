package nettee.jwt.issuer;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import nettee.jwt.api.HmacJwtSignatureAlgorithm;
import nettee.jwt.api.JwtSignatureAlgorithm;

import java.util.Date;
import java.util.Map;
import java.util.function.BiFunction;

public class JwtIssuer implements BiFunction<String, Map<String, ?>, String> {

    private final Long maxAge;
    private final JwtBuilder jwtBuilder;

    /**
     * HMAC-based signing.
     *
     * @param base64Key         the secret key as a base64-encoded string
     * @param maxAgeInSeconds   the maximum validity period of the token, in seconds
     * @param algorithm         the HMAC signature algorithm to use (HS256, HS384, or HS512)
     */
    public JwtIssuer(
            String base64Key,
            long maxAgeInSeconds,
            HmacJwtSignatureAlgorithm algorithm
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(base64Key);
        var secretKey = Keys.hmacShaKeyFor(keyBytes);

        this.maxAge = maxAgeInSeconds;
        this.jwtBuilder = Jwts.builder()
                .signWith(
                        secretKey,
                        algorithm.jjwtSignatureAlgorithm()
                );
    }

    /**
     * Create a new JwtIssuer for asymmetric (RSA, EC, or EdDSA) signing.
     *
     * @param maxAge               the maximum validity period of the token, in seconds
     * @param base64PrivateKey     the private key in PKCS#8 format, as a base64-encoded string
     * @param algorithm            the asymmetric signature algorithm to use
     *                             (RS256, RS384, RS512, PS256, PS384, PS512, ES256, ES384, ES512, or EdDSA)
     */
    public JwtIssuer(
            Long maxAge,
            String base64PrivateKey,
            JwtSignatureAlgorithm algorithm
    ) {
        this.maxAge = maxAge;

        this.jwtBuilder = Jwts.builder()
                .signWith(
                        algorithm.privateKeyFromBase64(base64PrivateKey),
                        algorithm.jjwtSignatureAlgorithm()
                );
    }

    public String issue(String subject, Map<String, ?> extendedClaims) {
        return apply(subject, extendedClaims);
    }

    @Override
    public String apply(String subject, Map<String, ?> extendedClaims) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + maxAge * 1000);

        /* ╔══════════════════════════════════════════════════╗
           ║                      HEADER:                     ║
           ║ .signWith(jwtProperties.secretKey(), SIG.HS512)  ║
           ╠══════════════════════════════════════════════════╣
           ║                      PAYLOAD                     ║ */
        return jwtBuilder.claims()
                .subject(subject)
                .add(extendedClaims)
                .issuedAt(now)
                .expiration(expiration)
                .and()
        /* ╠══════════════════════════════════════════════════╣ */
                .compact();
        /* ║                     COMPLETE                     ║
           ║            SIGNATURE is auto-generated           ║
           ╚══════════════════════════════════════════════════╝ */
    }
}
