package nettee.notification.exception;

import java.util.Map;
import java.util.function.Supplier;
import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

public enum NotificationCommandErrorCode implements ErrorCode {

    NOTIFICATION_NOT_FOUND("알림을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    NOTIFICATION_ALREADY_EXIST("알림이 이미 존재합니다.", HttpStatus.CONFLICT),
    DEFAULT("알림 조작 오류", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus httpStatus;

    NotificationCommandErrorCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String message() { return message; }

    @Override
    public HttpStatus httpStatus() { return httpStatus; }

    @Override
    public NotificationCommandException exception() { return new NotificationCommandException(this);}

    @Override
    public NotificationCommandException exception(Throwable cause) {
        return new NotificationCommandException(this, cause);
    }

    @Override
    public NotificationCommandException exception(Runnable runnable) {
        return new NotificationCommandException(this, runnable);
    }

    @Override
    public NotificationCommandException exception(Runnable runnable, Throwable cause) {
        return new NotificationCommandException(this, runnable, cause);
    }

    @Override
    public NotificationCommandException exception(Supplier<Map<String, Object>> payload) {
        return new NotificationCommandException(this, payload);
    }

    @Override
    public NotificationCommandException exception(Supplier<Map<String, Object>> payload,
        Throwable cause) {
        return new NotificationCommandException(this, payload, cause);
    }
}
