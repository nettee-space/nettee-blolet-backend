package nettee.blolet.article.exception;

import nettee.common.CustomException;

import java.util.Map;
import java.util.function.Supplier;

public class SeriesArticleException extends CustomException {

    public SeriesArticleException(SeriesArticleErrorCode errorCode) {
        super(errorCode);
    }

    public SeriesArticleException(SeriesArticleErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public SeriesArticleException(SeriesArticleErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public SeriesArticleException(SeriesArticleErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public SeriesArticleException(SeriesArticleErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public SeriesArticleException(SeriesArticleErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
