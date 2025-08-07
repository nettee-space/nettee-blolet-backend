package nettee.auth.exception;

import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Supplier;

public enum AuthErrorCode implements ErrorCode {
    // web 요청 관련 오류
    AUTH_LOGIN_ID_REQUIRED("로그인 ID는 필수입니다.", HttpStatus.BAD_REQUEST),
    AUTH_USERNAME_REQUIRED("사용자 이름은 필수입니다.", HttpStatus.BAD_REQUEST),
    AUTH_PASSWORD_REQUIRED("비밀번호는 필수입니다.", HttpStatus.BAD_REQUEST),
    AUTH_EMAIL_REQUIRED("이메일은 필수입니다.", HttpStatus.BAD_REQUEST),
    AUTH_OTP_REQUIRED("이메일 OTP 코드는 필수입니다.", HttpStatus.BAD_REQUEST),
    AUTH_NONCE_REQUIRED("nonce 토큰은 필수입니다.", HttpStatus.BAD_REQUEST),

    // domain 비즈니스 관련 오류
    AUTH_ACCOUNT_ALREADY_EXIST("이미 존재하는 계정입니다.", HttpStatus.CONFLICT),
    AUTH_ACCOUNT_NOT_FOUND("사용자 계정을 찾을 수 없습니다.", HttpStatus.LOCKED),
    AUTH_PASSWORD_MISMATCHED("사용자 비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED),
    AUTH_OTP_INVALID("유효하지 않은 OTP 입니다.", HttpStatus.UNAUTHORIZED),
    AUTH_OTP_SERIALIZE_FAILED("OTP 직렬화에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    AUTH_OTP_DESERIALIZE_FAILED("OTP 역직렬화에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    // rdb 관련 오류
    AUTH_USER_STATUS_INVALID("유효하지 않는 유저 상태입니다.", HttpStatus.BAD_REQUEST),
    ;
    
    private final String message;
    private final HttpStatus httpStatus;

    AuthErrorCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }

    @Override
    public AuthException exception() {
        return new AuthException(this);
    }

    @Override
    public AuthException exception(Throwable cause) {
        return new AuthException(this, cause);
    }

    @Override
    public RuntimeException exception(Runnable runnable) {
        return new AuthException(this, runnable);
    }

    @Override
    public RuntimeException exception(Runnable runnable, Throwable cause) {
        return new AuthException(this, runnable, cause);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload) {
        return new AuthException(this, payload);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload, Throwable cause) {
        return new AuthException(this, payload, cause);
    }
}