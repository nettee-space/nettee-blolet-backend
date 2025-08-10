package nettee.auth.util;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import nettee.auth.config.RefreshTokenConfig;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CookieUtil {
    
    private final RefreshTokenConfig config;
    
    public ResponseCookie createRefreshTokenCookie(String refreshToken) {
        ResponseCookie.ResponseCookieBuilder builder = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(config.isHttpOnly())
                .secure(config.isSecure())
                .path(config.getPath())
                .maxAge(Duration.ofDays(config.getMaxAge()))
                .sameSite(config.getSameSite());
        
        return builder.build();
    }
}
