package nettee.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Argon2PasswordEncoder 설정
        // saltLength=16, hashLength=32, parallelism=1, memory=32768 (32MiB), iterations=4
        return new Argon2PasswordEncoder(16, 32, 1, 32768, 4);
    }
}
