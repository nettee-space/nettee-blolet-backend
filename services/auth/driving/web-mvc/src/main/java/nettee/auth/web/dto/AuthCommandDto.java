package nettee.auth.web.dto;

import static nettee.auth.exception.AuthErrorCode.AUTH_EMAIL_REQUIRED;
import static nettee.auth.exception.AuthErrorCode.AUTH_LOGIN_ID_REQUIRED;
import static nettee.auth.exception.AuthErrorCode.AUTH_NONCE_REQUIRED;
import static nettee.auth.exception.AuthErrorCode.AUTH_OTP_REQUIRED;
import static nettee.auth.exception.AuthErrorCode.AUTH_PASSWORD_INVALID_FORMAT;
import static nettee.auth.exception.AuthErrorCode.AUTH_PASSWORD_INVALID_LENGTH;
import static nettee.auth.exception.AuthErrorCode.AUTH_PASSWORD_REQUIRED;
import static nettee.auth.exception.AuthErrorCode.AUTH_USERNAME_REQUIRED;
import static nettee.common.validation.Preconditions.validateLength;
import static nettee.common.validation.Preconditions.validateNotBlank;
import static nettee.common.validation.Preconditions.validateRegex;

import io.swagger.v3.oas.annotations.media.Schema;

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
            @Schema(description = "이메일", example = "sun@gmail.com")
            String email,
            @Schema(description = "이용 약관 동의 여부", example = "true")
            boolean agreedTerms,
            @Schema(description = "개인정보 처리 방침 동의 여부", example = "true")
            boolean agreedPrivacy
    ) {
        public SignUpRequest {
            // 필수 값 검증
            validateNotBlank(loginId, AUTH_LOGIN_ID_REQUIRED);
            validateNotBlank(username, AUTH_USERNAME_REQUIRED);
            validateNotBlank(password, AUTH_PASSWORD_REQUIRED);
            validateNotBlank(email, AUTH_EMAIL_REQUIRED);

            loginId = loginId.strip();
            username = username.strip();
            password = password.strip();
            email = email.strip();

            // 정규식 검증
            final String PASSWORD_REGEX = "^[A-Za-z\\d !\"#$%&'()*+,\\-./:;<=>?@\\[\\]^_`{|}~]+$";
            final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            validateRegex(password, PASSWORD_REGEX, AUTH_PASSWORD_INVALID_FORMAT);
            validateRegex(email, EMAIL_REGEX, AUTH_EMAIL_REQUIRED);

            // 비밀번호 길이 검증
            validateLength(password, 8, 64, AUTH_PASSWORD_INVALID_LENGTH);
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
            // 필수 값 검증
            validateNotBlank(loginId, AUTH_LOGIN_ID_REQUIRED);
            validateNotBlank(password, AUTH_PASSWORD_REQUIRED);

            loginId = loginId.strip();
            password = password.strip();

            // 비밀번호 정규식 검증
            final String PASSWORD_REGEX = "^[A-Za-z\\d !\"#$%&'()*+,\\-./:;<=>?@\\[\\]^_`{|}~]+$";
            validateRegex(password, PASSWORD_REGEX, AUTH_PASSWORD_INVALID_FORMAT);
        }
    }

    @Schema(description = "이메일 인증코드 전송 요청")
    public record EmailVerifySendRequest(
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

    @Schema(description = "이메일 비밀번호 재설정 전송 요청")
    public record PasswordForgotRequest(
            @Schema(description = "이메일", example = "sun@gmail.com")
            String email
    ) {
        public PasswordForgotRequest {
            validateNotBlank(email, AUTH_EMAIL_REQUIRED);
            email = email.strip();
        }
    }

    @Schema(description = "비밀번호 재설정")
    public record PasswordResetRequest(
            @Schema(description = "이메일", example = "sun@gmail.com")
            String email,
            @Schema(description = "변경 비밀번호", example = "Blolet0101!")
            String newPassword
    ) {
        public PasswordResetRequest {
            validateNotBlank(email, AUTH_EMAIL_REQUIRED);
            validateNotBlank(newPassword, AUTH_PASSWORD_REQUIRED);

            email = email.strip();
            newPassword = newPassword.strip();

            // 비밀번호 정규식 검증
            final String PASSWORD_REGEX = "^[A-Za-z\\d !\"#$%&'()*+,\\-./:;<=>?@\\[\\]^_`{|}~]+$";
            validateRegex(newPassword, PASSWORD_REGEX, AUTH_PASSWORD_INVALID_FORMAT);
        }
    }

    @Schema(description = "로그인 응답")
    public record LoginResponse(
            @Schema(description = "액세스 토큰(jwt)", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdW4xMjMiLCJpYXQiOjE2NTY1MjY4MDB9.a_XGbI5TW6G5lNO5R4uK_KPOtVz7b5Ue8i6W0Or6BlY")
            String accessToken
    ) {
    }
}
