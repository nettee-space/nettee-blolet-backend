package nettee.article.exception;

import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Supplier;

public enum ArticleErrorCode implements ErrorCode {
    ARTICLE_NOT_FOUND("아티클을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    DEFAULT("아티클 조작 오류", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus httpStatus;

    ArticleErrorCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }

    @Override
    public ArticleException exception() {
        return new ArticleException(this);
    }

    @Override
    public ArticleException exception(Throwable cause) {
        return new ArticleException(this, cause);
    }

    @Override
    public RuntimeException exception(Runnable runnable) {
        return new ArticleException(this, runnable);
    }

    @Override
    public RuntimeException exception(Runnable runnable, Throwable cause) {
        return new ArticleException(this, runnable, cause);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload) {
        return new ArticleException(this, payload);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload, Throwable cause) {
        return new ArticleException(this, payload, cause);
    }
}
