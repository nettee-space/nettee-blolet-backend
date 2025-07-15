package nettee.usersettings.exception;

import java.util.Map;
import java.util.function.Supplier;
import nettee.common.CustomException;

public class NotificationSettingsQueryException extends CustomException {

    public NotificationSettingsQueryException(NotificationSettingsQueryErrorCode errorCode) {
        super(errorCode);
    }

    public NotificationSettingsQueryException(NotificationSettingsQueryErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public NotificationSettingsQueryException(NotificationSettingsQueryErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public NotificationSettingsQueryException(NotificationSettingsQueryErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public NotificationSettingsQueryException(NotificationSettingsQueryErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public NotificationSettingsQueryException(NotificationSettingsQueryErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
