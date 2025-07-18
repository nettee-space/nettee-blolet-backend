package nettee.notification.exception;

import java.util.Map;
import java.util.function.Supplier;
import nettee.common.CustomException;
import nettee.common.ErrorCode;

public class NotificationCommandException extends CustomException {

    public NotificationCommandException(ErrorCode errorCode) { super(errorCode); }

    public NotificationCommandException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public NotificationCommandException(ErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public NotificationCommandException(ErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public NotificationCommandException(ErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public NotificationCommandException(ErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
