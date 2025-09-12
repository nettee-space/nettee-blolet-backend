package nettee.profile.exception;

import java.util.Map;
import java.util.function.Supplier;
import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

public enum ProfileErrorCode implements ErrorCode {
    // web 요청 관련 오류

    // domain 비즈니스 관련 오류
    PROFILE_ALREADY_EXIST("사용자의 프로필이 존재합니다.", HttpStatus.CONFLICT),

    // rdb 관련 오류
    ;

    private final String message;
    private final HttpStatus httpStatus;

    ProfileErrorCode(String message, HttpStatus httpStatus) {
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
    public ProfileException exception() {
        return new ProfileException(this);
    }

    @Override
    public ProfileException exception(Throwable cause) {
        return new ProfileException(this, cause);
    }

    @Override
    public RuntimeException exception(Runnable runnable) {
        return new ProfileException(this, runnable);
    }

    @Override
    public RuntimeException exception(Runnable runnable, Throwable cause) {
        return new ProfileException(this, runnable, cause);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload) {
        return new ProfileException(this, payload);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload, Throwable cause) {
        return new ProfileException(this, payload, cause);
    }
}
