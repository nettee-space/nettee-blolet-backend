package nettee.auth.web.dto;

import static nettee.auth.exception.AuthErrorCode.AUTH_EMAIL_REQUIRED;
import static nettee.auth.exception.AuthErrorCode.AUTH_LOGIN_ID_REQUIRED;
import static nettee.auth.exception.AuthErrorCode.AUTH_NONCE_REQUIRED;
import static nettee.auth.exception.AuthErrorCode.AUTH_OTP_REQUIRED;
import static nettee.auth.exception.AuthErrorCode.AUTH_PASSWORD_REQUIRED;
import static nettee.auth.exception.AuthErrorCode.AUTH_USERNAME_REQUIRED;
import static nettee.common.validation.Preconditions.validateNotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

public final class AuthCommandDto {
    private AuthCommandDto() {
    }

    @Schema(description = "회원가입 요청")
    public record SignUpRequest(
            @Schema(description = "로그인 ID", example = "sun123")
            String loginId,
            @Schema(description = "사용자 이름", example = "sun")
            String username,
            @Schema(description = "비밀번호", example = "Blolet1225!")
            String password,
            @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
            @Schema(description = "이메일", example = "sun@gmail.com")
            String email,
            @Schema(description = "이용 약관 동의 여부", example = "true")
            boolean agreedTerms,
            @Schema(description = "개인정보 처리 방침 동의 여부", example = "true")
            boolean agreedPrivacy
    ) {
        public SignUpRequest {
            validateNotBlank(loginId, AUTH_LOGIN_ID_REQUIRED);
            validateNotBlank(username, AUTH_USERNAME_REQUIRED);
            validateNotBlank(password, AUTH_PASSWORD_REQUIRED);
            validateNotBlank(email, AUTH_EMAIL_REQUIRED);

            loginId = loginId.strip();
            username = username.strip();
            password = password.strip();
            email = email.strip();
        }
    }

    @Schema(description = "로그인 요청")
    public record LoginRequest(
            @Schema(description = "로그인 ID", example = "sun123")
            String loginId,
            @Schema(description = "비밀번호", example = "Blolet1225!")
            String password
    ) {
        public LoginRequest {
            validateNotBlank(loginId, AUTH_LOGIN_ID_REQUIRED);
            validateNotBlank(password, AUTH_PASSWORD_REQUIRED);

            loginId = loginId.strip();
            password = password.strip();
        }
    }

    @Schema(description = "이메일 인증코드 전송 요청")
    public record EmailVerifySendRequest(
            @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
            @Schema(description = "이메일", example = "sun@gmail.com")
            String email
    ) {
        public EmailVerifySendRequest {
            validateNotBlank(email, AUTH_EMAIL_REQUIRED);

            email = email.strip();
        }
    }

    @Schema(description = "이메일 인증코드 확인 요청")
    public record EmailVerifyRequest(
            @Schema(description = "이메일", example = "sun@gmail.com")
            String email,
            @Schema(description = "이메일 인증 코드", example = "123456")
            String otp,
            @Schema(description = "클라이언트 식별자", example = "nonce123")
            String nonce
    ) {
        public EmailVerifyRequest {
            validateNotBlank(email, AUTH_EMAIL_REQUIRED);
            validateNotBlank(otp, AUTH_OTP_REQUIRED);
            validateNotBlank(nonce, AUTH_NONCE_REQUIRED);

            email = email.strip();
            otp = otp.strip();
            nonce = nonce.strip();
        }
    }

    @Schema(description = "비밀번호 변경 전 재인증 요청")
    public record PasswordResetRequest(
            @Schema(description = "현재 사용중인 비밀번호", example = "Blolet1225!")
            String password
    ) {
    }

    @Schema(description = "비밀번호 변경 요청")
    public record PasswordVerifyRequest(
            @Schema(description = "변경하고자 하는 비밀번호", example = "Blolet1225!")
            String password
    ) {
    }

    @Schema(description = "로그인 응답")
    public record LoginResponse(
            @Schema(description = "액세스 토큰(jwt)", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdW4xMjMiLCJpYXQiOjE2NTY1MjY4MDB9.a_XGbI5TW6G5lNO5R4uK_KPOtVz7b5Ue8i6W0Or6BlY")
            String accessToken
    ) {
    }
}
