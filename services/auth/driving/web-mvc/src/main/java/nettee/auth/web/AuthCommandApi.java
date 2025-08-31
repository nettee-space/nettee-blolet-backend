package nettee.auth.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nettee.auth.usecase.AuthSignUsecase;
import nettee.auth.util.CookieUtil;
import nettee.auth.web.dto.AuthCommandDto.EmailVerifyRequest;
import nettee.auth.web.dto.AuthCommandDto.EmailVerifySendRequest;
import nettee.auth.web.dto.AuthCommandDto.LoginRequest;
import nettee.auth.web.dto.AuthCommandDto.LoginResponse;
import nettee.auth.web.dto.AuthCommandDto.PasswordForgotRequest;
import nettee.auth.web.dto.AuthCommandDto.PasswordResetRequest;
import nettee.auth.web.dto.AuthCommandDto.SignUpRequest;
import nettee.auth.web.mapper.AuthDtoMapper;
import nettee.blolet.auth.readmodel.AuthCommandModels.LoginTokenModel;
import nettee.blolet.auth.readmodel.AuthCommandModels.SignUpRequestModel;
import nettee.blolet.jwt.filter.annotation.AuthUser;
import nettee.blolet.jwt.filter.annotation.AuthorizedUser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
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

    private final AuthSignUsecase authSignUsecase;
    private final AuthDtoMapper mapper;
    private final CookieUtil cookieUtil;

    @PostMapping("/signup")
    @Operation(
            summary = "회원가입",
            description = "일반 사용자를 등록합니다."
    )
    public ResponseEntity<LoginResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        // 회원 가입 로직 구현
        SignUpRequestModel requestModel = mapper.toModel(signUpRequest);
        LoginTokenModel responseModel = authSignUsecase.signUp(requestModel);
        LoginResponse result = mapper.toDto(responseModel);

        ResponseCookie refreshTokenCookie = cookieUtil.createRefreshTokenCookie(responseModel.refreshToken());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(result);
    }

    @PostMapping("/login")
    @Operation(
            summary = "로그인",
            description = """
                일반 사용자가 로그인합니다.
                리프레시 토큰(refreshToken)은 HttpOnly 쿠키로 반환합니다.
            """
    )
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        // 로그인 로직 구현
        LoginTokenModel responseModel = authSignUsecase.signIn(loginRequest.loginId(), loginRequest.password());
        LoginResponse result = mapper.toDto(responseModel);

        ResponseCookie refreshTokenCookie = cookieUtil.createRefreshTokenCookie(responseModel.refreshToken());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(result);
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃", description = "사용자가 로그아웃합니다.")
    public ResponseEntity<Void> logout(@AuthUser AuthorizedUser authorizedUser,
                                       @CookieValue("refreshToken") String refreshToken) {
        String userId = authorizedUser.userId();
        authSignUsecase.logout(userId, refreshToken);

        ResponseCookie refreshTokenCookie = cookieUtil.deleteRefreshTokenCookie();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .build();
    }

    @DeleteMapping("/withdraw")
    @Operation(
            summary = "회원탈퇴",
            description = "사용자가 탈퇴합니다."
    )
    public ResponseEntity<Void> withdraw(@AuthUser AuthorizedUser authorizedUser,
                                           @CookieValue("refreshToken") String refreshToken) {
        String userId = authorizedUser.userId();
        authSignUsecase.withdraw(userId, refreshToken);

        ResponseCookie refreshTokenCookie = cookieUtil.deleteRefreshTokenCookie();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .build();
    }

    @PostMapping("/email/verification/send")
    @Operation(
            summary = "이메일 인증코드 전송",
            description = "사용자의 이메일로 인증코드를 전송합니다."
    )
    public ResponseEntity<String> sendEmailVerification(@RequestBody EmailVerifySendRequest request) {
        String token = authSignUsecase.sendOtp(request.email());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/email/verification/check")
    @Operation(
            summary = "이메일 인증코드 확인",
            description = "사용자가 이메일 인증코드를 확인합니다."
    )
    public ResponseEntity<String> verifyEmail(@RequestBody EmailVerifyRequest request) {
        String token = authSignUsecase.verifyOtp(request.email(), request.otp(), request.nonce());
        return ResponseEntity.ok(token);
    }

    @PostMapping("email/password/reset/send")
    @Operation(
            summary = "이메일 비밀번호 재설정 링크 전송",
            description = "사용자의 이메일로 비밀번호를 변경할 수 있는 링크를 발송합니다."
    )
    public ResponseEntity<String> sendPasswordResetEmail(@RequestBody PasswordForgotRequest request) {
        String nonce = authSignUsecase.sendPasswordResetEmail(request.email());
        return ResponseEntity.ok(nonce);
    }

    @PostMapping("email/password/reset")
    @Operation(
            summary = "이메일 비밀번호 재설정",
            description = "사용자가 이메일 링크를 통해 비밀번호를 변경합니다."
    )
    public void resetPassword(@RequestBody PasswordResetRequest request) {
        authSignUsecase.resetPassword(request.email(), request.newPassword(), request.nonce());
    }

    @PostMapping("/token/refresh")
    @Operation(
            summary = "액세스 토큰 재발급",
            description = "리프레시 토큰(HttpOnly 쿠키)을 이용해 새로운 액세스 토큰을 발급받습니다."
    )
    public ResponseEntity<LoginResponse> refreshAccessToken(@AuthUser AuthorizedUser authorizedUser,
                                            @CookieValue("refreshToken") String refreshToken) {
        LoginTokenModel responseModel = authSignUsecase.refreshAccessToken(authorizedUser.userId(), refreshToken);
        LoginResponse result = mapper.toDto(responseModel);

        ResponseCookie refreshTokenCookie = cookieUtil.createRefreshTokenCookie(responseModel.refreshToken());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(result);
    }

    // TODO: 아이디/비밀번호 찾기 추가 기능 구현 가능성 있음
}
