package nettee.draft.draftblock.exception;

import nettee.common.CustomException;

import java.util.Map;
import java.util.function.Supplier;

public class DraftBlockQueryException extends CustomException {
    public DraftBlockQueryException(DraftBlockQueryErrorCode errorCode) {
        super(errorCode);
    }

    public DraftBlockQueryException(DraftBlockQueryErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public DraftBlockQueryException(DraftBlockQueryErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public DraftBlockQueryException(DraftBlockQueryErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public DraftBlockQueryException(DraftBlockQueryErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public DraftBlockQueryException(DraftBlockQueryErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
