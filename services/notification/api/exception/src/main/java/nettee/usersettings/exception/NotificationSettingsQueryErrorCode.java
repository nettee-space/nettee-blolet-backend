package nettee.usersettings.exception;

import java.util.Map;
import java.util.function.Supplier;
import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

public enum NotificationSettingsQueryErrorCode implements ErrorCode {
    NOTIFICATION_SETTINGS_NOT_FOUND("알림 설정을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    NOTIFICATION_SETTINGS_FORBIDDEN("알림 설정을 조회할 권한이 없습니다.", HttpStatus.FORBIDDEN),
    DEFAULT("알림 설정 조회 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus httpStatus;

    NotificationSettingsQueryErrorCode(String message, HttpStatus httpStatus) {
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
    public NotificationSettingsQueryException exception() {
        return new NotificationSettingsQueryException(this);
    }

    @Override
    public NotificationSettingsQueryException exception(Throwable cause) {
        return new NotificationSettingsQueryException(this, cause);
    }

    @Override
    public NotificationSettingsQueryException exception(Runnable runnable) {
        return new NotificationSettingsQueryException(this, runnable);
    }

    @Override
    public NotificationSettingsQueryException exception(Runnable runnable, Throwable cause) {
        return new NotificationSettingsQueryException(this, runnable, cause);
    }

    @Override
    public NotificationSettingsQueryException exception(Supplier<Map<String, Object>> payload) {
        return new NotificationSettingsQueryException(this, payload);
    }

    @Override
    public NotificationSettingsQueryException exception(Supplier<Map<String, Object>> payload, Throwable cause) {
        return new NotificationSettingsQueryException(this, payload, cause);
    }
}
