package nettee.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
public record JwtProperties(
    String keyType,
    String secretKey,
    String privateKey,
    long accessTokenMaxAgeSeconds,
    long refreshTokenMaxAgeSeconds
) {
}
