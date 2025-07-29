package nettee.article.exception;

import nettee.common.CustomException;
import nettee.common.ErrorCode;

import java.util.Map;
import java.util.function.Supplier;

public class ArticleException extends CustomException {

    public ArticleException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ArticleException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public ArticleException(ErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public ArticleException(ErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public ArticleException(ErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public ArticleException(ErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
