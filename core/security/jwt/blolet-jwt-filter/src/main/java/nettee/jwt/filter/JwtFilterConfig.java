package nettee.jwt.filter;

import java.util.Set;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class JwtFilterConfig {
    
    /**
     * JWT 토큰 검증이 필요하지 않은 경로들
     */
    private final Set<String> excludePaths = Set.of(
        "/auth/signup",
        "/auth/login",
        "/auth/email/verification/send",
        "/auth/email/verification/check",
        "/auth/token/refresh",
        "/swagger-ui"
    );
}

