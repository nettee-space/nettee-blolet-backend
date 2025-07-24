package nettee.series.readmodel;

import lombok.Builder;

public final class AuthCommandModels {

    private AuthCommandModels() {}

    @Builder
    public record LoginToken (
        String loginId,
        String accessToken,
        String refreshToken,
        String refreshTokenKey
    ) {
    }
}
