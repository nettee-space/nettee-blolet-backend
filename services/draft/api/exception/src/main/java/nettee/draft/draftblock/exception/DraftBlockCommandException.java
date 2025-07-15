package nettee.draft.draftblock.exception;

import nettee.common.CustomException;
import nettee.common.ErrorCode;

import java.util.Map;
import java.util.function.Supplier;

public class DraftBlockCommandException extends CustomException {

    public DraftBlockCommandException(ErrorCode errorCode) {
        super(errorCode);
    }

    public DraftBlockCommandException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public DraftBlockCommandException(ErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public DraftBlockCommandException(ErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public DraftBlockCommandException(ErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public DraftBlockCommandException(ErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
