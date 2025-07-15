package nettee.auth.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nettee.auth.web.dto.AuthCommandDto.EmailVerifyRequest;
import nettee.auth.web.dto.AuthCommandDto.EmailVerifySendRequest;
import nettee.auth.web.dto.AuthCommandDto.LoginRequest;
import nettee.auth.web.dto.AuthCommandDto.LoginResponse;
import nettee.auth.web.dto.AuthCommandDto.PasswordResetRequest;
import nettee.auth.web.dto.AuthCommandDto.PasswordVerifyRequest;
import nettee.auth.web.dto.AuthCommandDto.SignUpRequest;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "인증 관련 API")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthCommandApi {

    @PostMapping("/sign-up")
    @Operation(
            summary = "회원가입",
            description = "일반 사용자를 등록합니다."
    )
    public void signUp(@RequestBody SignUpRequest signUpRequest) {
        // 회원 가입 로직 구현
    }

    @PostMapping("/log-in")
    @Operation(
            summary = "로그인",
            description = """
                일반 사용자가 로그인합니다.
                리프레시 토큰(refreshToken)은 HttpOnly 쿠키로 반환합니다.
            """
    )
    public LoginResponse logIn(@RequestBody LoginRequest loginRequest) {
        // 로그인 로직 구현
        return null;
    }

    @PostMapping("/log-out")
    @Operation(summary = "로그아웃", description = "사용자가 로그아웃합니다.")
    public void logOut() {
        // 로그아웃 로직 구현
    }

    // TODO: loginId가 Unique 하며 NOT NULL 일 시, loginId로 회원 탈퇴를 진행할 수 있도록 변경 가능
    @DeleteMapping("/auth/withdraw")
    @Operation(
            summary = "회원탈퇴",
            description = "사용자가 탈퇴합니다."
    )
    public void withdraw(String userId) {
        // 회원 탈퇴 구현
    }

    @PostMapping("/email/verification/send")
    @Operation(
            summary = "이메일 인증코드 전송",
            description = "사용자의 이메일로 인증코드를 전송합니다."
    )
    public void sendEmailVerification(@RequestBody EmailVerifySendRequest emailVerifySendRequest) {
        // 이메일 인증 코드 전송 로직 구현
    }

    @PostMapping("/email/verification/check")
    @Operation(
            summary = "이메일 인증코드 확인",
            description = "사용자가 이메일 인증코드를 확인합니다."
    )
    public void verifyEmail(@RequestBody EmailVerifyRequest emailVerifyRequest) {
        // 이메일 인증 코드 확인 로직 구현
    }

    // TODO: 비밀번호 변경 전 재인증 & 비밀번호 변경을 함께 진행할 수도 있음
    @PostMapping("/password/verification")
    @Operation(
            summary = "비밀번호 변경 전 재인증",
            description = "사용자가 비밀번호를 변경하기 전에 재인증을 수행합니다."
    )
    public void changePassword(@RequestBody PasswordVerifyRequest passwordVerifyRequest) {
        // 비밀번호 변경 로직 구현
    }

    @PostMapping("/password/reset")
    @Operation(
            summary = "비밀번호 변경",
            description = "사용자의 비밀번호를 변경합니다."
    )
    public void resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) {
        // 비밀번호 재설정 링크 전송 로직 구현
    }

    @PostMapping("/token/refresh")
    @Operation(
            summary = "액세스 토큰 재발급",
            description = "리프레시 토큰(HttpOnly 쿠키)을 이용해 새로운 액세스 토큰을 발급받습니다."
    )
    public LoginResponse refreshAccessToken(@CookieValue("refreshToken") String refreshToken) {
        // 액세스 토큰 재발급 로직 구현
        return null;
    }

    // TODO: 아이디/비밀번호 찾기 추가 기능 구현 가능성 있음
}
