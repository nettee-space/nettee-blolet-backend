package nettee.usersettings.exception;

import java.util.Map;
import java.util.function.Supplier;
import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

public enum NotificationSettingsCommandErrorCode implements ErrorCode {

    NOTIFICATION_SETTINGS_NOT_FOUND("알림 설정을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    NOTIFICATION_SETTINGS_INVALID("알림 설정 값이 유효하지 않습니다.", HttpStatus.BAD_REQUEST),
    DEFAULT("알림 설정 처리 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus httpStatus;

    NotificationSettingsCommandErrorCode(String message, HttpStatus httpStatus) {
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
    public NotificationSettingsCommandException exception() {
        return new NotificationSettingsCommandException(this);
    }

    @Override
    public NotificationSettingsCommandException exception(Throwable cause) {
        return new NotificationSettingsCommandException(this, cause);
    }

    @Override
    public NotificationSettingsCommandException exception(Runnable runnable) {
        return new NotificationSettingsCommandException(this, runnable);
    }

    @Override
    public NotificationSettingsCommandException exception(Runnable runnable, Throwable cause) {
        return new NotificationSettingsCommandException(this, runnable, cause);
    }

    @Override
    public NotificationSettingsCommandException exception(Supplier<Map<String, Object>> payload) {
        return new NotificationSettingsCommandException(this, payload);
    }

    @Override
    public NotificationSettingsCommandException exception(Supplier<Map<String, Object>> payload, Throwable cause) {
        return new NotificationSettingsCommandException(this, payload, cause);
    }
}
