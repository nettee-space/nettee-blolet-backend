package nettee.blolet.article.exception;

import nettee.common.CustomException;

import java.util.Map;
import java.util.function.Supplier;

public class SeriesException extends CustomException {

    public SeriesException(SeriesErrorCode errorCode) {
        super(errorCode);
    }

    public SeriesException(SeriesErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public SeriesException(SeriesErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public SeriesException(SeriesErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public SeriesException(SeriesErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public SeriesException(SeriesErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
