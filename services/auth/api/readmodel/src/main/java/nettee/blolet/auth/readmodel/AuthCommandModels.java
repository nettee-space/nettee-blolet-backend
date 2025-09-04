package nettee.blolet.auth.readmodel;

import lombok.Builder;

public final class AuthCommandModels {

    public record SignUpRequestModel(
            String username,
            String email,
            String password,
            String nickname,

            boolean agreedTerms,    // 이용약관 동의
            boolean agreedPrivacy   // 개인정보처리방침 동의
    ) {
    }

    @Builder
    public record LoginTokenModel(
            String accessToken,
            String refreshToken
    ) {
    }
}
