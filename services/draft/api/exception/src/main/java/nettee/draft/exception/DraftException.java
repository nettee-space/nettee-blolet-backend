package nettee.draft.exception;

import nettee.common.CustomException;
import nettee.common.ErrorCode;

import java.util.Map;
import java.util.function.Supplier;

public class DraftException extends CustomException {

    public DraftException(ErrorCode errorCode) {
        super(errorCode);
    }

    public DraftException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public DraftException(ErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public DraftException(ErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public DraftException(ErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public DraftException(ErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
