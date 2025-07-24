package nettee.auth.exception;

import nettee.common.CustomException;

import java.util.Map;
import java.util.function.Supplier;

public class AuthException extends CustomException {

    public AuthException(AuthErrorCode errorCode) {
        super(errorCode);
    }

    public AuthException(AuthErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public AuthException(AuthErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public AuthException(AuthErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public AuthException(AuthErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public AuthException(AuthErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
