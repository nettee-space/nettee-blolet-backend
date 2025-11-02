package nettee.profile.exception;

import java.util.Map;
import java.util.function.Supplier;
import nettee.common.CustomException;

public class ProfileException extends CustomException {

    public ProfileException(ProfileErrorCode errorCode) {
        super(errorCode);
    }

    public ProfileException(ProfileErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public ProfileException(ProfileErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public ProfileException(ProfileErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public ProfileException(ProfileErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public ProfileException(ProfileErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
