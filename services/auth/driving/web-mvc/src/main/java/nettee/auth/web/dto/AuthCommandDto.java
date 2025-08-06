package nettee.auth.web.dto;

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
            @Schema(description = "닉네임", example = "sun@gmail.com")
            String email,
            @Schema(description = "이용 약관 동의 여부", example = "true")
            boolean agreedTerms,
            @Schema(description = "개인정보 처리 방침 동의 여부", example = "true")
            boolean agreedPrivacy
    ) {
    }

    @Schema(description = "로그인 요청")
    public record LoginRequest(
            @Schema(description = "로그인 ID", example = "sun123")
            String loginId,
            @Schema(description = "비밀번호", example = "Blolet1225!")
            String password
    ) {
    }

    @Schema(description = "이메일 인증코드 전송 요청")
    public record EmailVerifySendRequest(
            @Schema(description = "이메일", example = "sun@gmail.com")
            String email
    ) {
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
