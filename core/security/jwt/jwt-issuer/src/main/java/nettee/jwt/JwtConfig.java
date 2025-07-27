package nettee.jwt;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfig {

    @Bean
    public JwtIssuer jwtIssuer(JwtProperties properties) {
        return new JwtIssuer(
                properties.keyType(),
                properties.secretKey(),
                properties.privateKey(),
                properties.accessTokenMaxAgeSeconds(),
                properties.refreshTokenMaxAgeSeconds());
    }
}
