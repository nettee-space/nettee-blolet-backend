package nettee.blolet.article.exception;

import nettee.common.CustomException;
import nettee.common.ErrorCode;

import java.util.Map;
import java.util.function.Supplier;

public class DraftBlockException extends CustomException {

    public DraftBlockException(ErrorCode errorCode) {
        super(errorCode);
    }

    public DraftBlockException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public DraftBlockException(ErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public DraftBlockException(ErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public DraftBlockException(ErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public DraftBlockException(ErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
