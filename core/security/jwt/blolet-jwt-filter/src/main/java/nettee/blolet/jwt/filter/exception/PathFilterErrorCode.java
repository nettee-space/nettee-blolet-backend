package nettee.blolet.jwt.filter.exception;

import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Supplier;

public enum PathFilterErrorCode implements ErrorCode {
    AUTH_FILTER_PATH_NOT_ENTERED(HttpStatus.INTERNAL_SERVER_ERROR, "경로를 입력해야 합니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    PathFilterErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
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
    public PathFilterException exception() {
        return new PathFilterException(this);
    }

    @Override
    public PathFilterException exception(Throwable cause) {
        return new PathFilterException(this, cause);
    }

    @Override
    public PathFilterException exception(Runnable runnable) {
        return new PathFilterException(this, runnable);
    }

    @Override
    public PathFilterException exception(Runnable runnable, Throwable cause) {
        return new PathFilterException(this, runnable, cause);
    }

    @Override
    public PathFilterException exception(Supplier<Map<String, Object>> appendPayload) {
        return new PathFilterException(this, appendPayload);
    }

    @Override
    public PathFilterException exception(Supplier<Map<String, Object>> appendPayload, Throwable cause) {
        return new PathFilterException(this, appendPayload, cause);
    }
}
