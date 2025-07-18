package nettee.notification.exception;

import java.util.Map;
import java.util.function.Supplier;
import nettee.common.CustomException;

public class NotificationQueryException extends CustomException {
    public NotificationQueryException(NotificationQueryErrorCode errorCode) {
        super(errorCode);
    }

    public NotificationQueryException(NotificationQueryErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public NotificationQueryException(NotificationQueryErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public NotificationQueryException(NotificationQueryErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public NotificationQueryException(NotificationQueryErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public NotificationQueryException(NotificationQueryErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
