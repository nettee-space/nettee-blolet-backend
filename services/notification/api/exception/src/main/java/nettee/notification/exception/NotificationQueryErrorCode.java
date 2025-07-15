package nettee.notification.exception;

import java.util.Map;
import java.util.function.Supplier;
import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

public enum NotificationQueryErrorCode implements ErrorCode {
    NOTIFICATION_NOT_FOUND("알림을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    NOTIFICATION_FORBIDDEN("알림을 조회할 권한이 없습니다.", HttpStatus.FORBIDDEN),
    DEFAULT("알림 조작 오류", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus httpStatus;

    NotificationQueryErrorCode(String message, HttpStatus httpStatus) {
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
    public NotificationQueryException exception() {
        return new NotificationQueryException(this);
    }

    @Override
    public NotificationQueryException exception(Throwable cause) {
        return new NotificationQueryException(this, cause);
    }

    @Override
    public NotificationQueryException exception(Runnable runnable) {
        return new NotificationQueryException(this, runnable);
    }

    @Override
    public NotificationQueryException exception(Runnable runnable, Throwable cause) {
        return new NotificationQueryException(this, runnable, cause);
    }

    @Override
    public NotificationQueryException exception(Supplier<Map<String, Object>> payload) {
        return new NotificationQueryException(this, payload);
    }

    @Override
    public NotificationQueryException exception(Supplier<Map<String, Object>> payload, Throwable cause) {
        return new NotificationQueryException(this, payload, cause);
    }
}
