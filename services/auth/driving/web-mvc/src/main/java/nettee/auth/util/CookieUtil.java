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

    /**
     * Refresh Token 쿠키를 생성합니다.
     */
    public ResponseCookie createRefreshTokenCookie(String refreshToken) {
        return ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(config.isHttpOnly())
                .secure(config.isSecure())
                .path(config.getPath())
                .maxAge(Duration.ofDays(config.getMaxAge()))
                .sameSite(config.getSameSite())
                .build();
    }

    /**
     * Refresh Token 쿠키를 삭제합니다.
     */
    public ResponseCookie deleteRefreshTokenCookie() {
        return ResponseCookie.from("refreshToken", "")
                .httpOnly(config.isHttpOnly())
                .secure(config.isSecure())
                .path(config.getPath())
                .maxAge(0)  // 즉시 만료
                .sameSite(config.getSameSite())
                .build();
    }
}
