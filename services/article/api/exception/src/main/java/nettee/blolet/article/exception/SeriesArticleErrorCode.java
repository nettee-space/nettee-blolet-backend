package nettee.blolet.article.exception;

import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Supplier;

public enum SeriesArticleErrorCode implements ErrorCode {
    SERIES_ARTICLE_NOT_FOUND("시리즈 게시물을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    SERIES_ARTICLE_FORBIDDEN("권한이 없습니다.", HttpStatus.FORBIDDEN),
    SERIES_ARTICLE_ALREADY_EXIST("시리즈 게시글이 이미 존재합니다.", HttpStatus.CONFLICT),
    DEFAULT("시리즈 게시글 조작 오류", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus httpStatus;

    SeriesArticleErrorCode(String message, HttpStatus httpStatus) {
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
    public SeriesArticleException exception() {
        return new SeriesArticleException(this);
    }

    @Override
    public SeriesArticleException exception(Throwable cause) {
        return new SeriesArticleException(this, cause);
    }

    @Override
    public RuntimeException exception(Runnable runnable) {
        return new SeriesArticleException(this, runnable);
    }

    @Override
    public RuntimeException exception(Runnable runnable, Throwable cause) {
        return new SeriesArticleException(this, runnable, cause);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload) {
        return new SeriesArticleException(this, payload);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload, Throwable cause) {
        return new SeriesArticleException(this, payload, cause);
    }
}
