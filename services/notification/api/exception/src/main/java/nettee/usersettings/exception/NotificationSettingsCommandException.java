package nettee.usersettings.exception;

import java.util.Map;
import java.util.function.Supplier;
import nettee.common.CustomException;

public class NotificationSettingsCommandException extends CustomException {

    public NotificationSettingsCommandException(NotificationSettingsCommandErrorCode errorCode) {
        super(errorCode);
    }

    public NotificationSettingsCommandException(NotificationSettingsCommandErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public NotificationSettingsCommandException(NotificationSettingsCommandErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public NotificationSettingsCommandException(NotificationSettingsCommandErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public NotificationSettingsCommandException(NotificationSettingsCommandErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public NotificationSettingsCommandException(NotificationSettingsCommandErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}