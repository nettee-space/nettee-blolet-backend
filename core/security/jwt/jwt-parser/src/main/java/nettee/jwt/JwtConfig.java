package nettee.jwt;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfig {

    @Bean
    public JwtParser jwtParser(JwtProperties properties) {
        return new JwtParser(
                properties.keyType(),
                properties.secretKey(),
                properties.publicKey());
    }
}
