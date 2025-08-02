package nettee.auth.exception;

import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Supplier;

public enum AuthErrorCode implements ErrorCode {
    AUTH_ACCOUNT_ALREADY_EXIST("이미 존재하는 계정입니다.", HttpStatus.CONFLICT),
    AUTH_ACCOUNT_NOT_FOUND("사용자 계정을 찾을 수 없습니다.", HttpStatus.LOCKED),
    AUTH_PASSWORD_MISMATCHED("사용자 비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED),
    AUTH_OTP_INVALID("유효하지 않은 OTP 입니다.", HttpStatus.UNAUTHORIZED),
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