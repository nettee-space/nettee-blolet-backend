package nettee.blolet.auth.readmodel;

public final class AuthCommandModels {

    public record SignUpRequestModel(
            String loginId,
            String password,
            String nickname,
            String email,

            boolean agreedTerms,    // 이용약관 동의
            boolean agreedPrivacy   // 개인정보처리방침 동의
    ) {
    }
}
