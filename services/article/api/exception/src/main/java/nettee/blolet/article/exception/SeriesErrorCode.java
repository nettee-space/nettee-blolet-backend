package nettee.blolet.article.exception;

import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Supplier;

public enum SeriesErrorCode implements ErrorCode {
    SERIES_NOT_FOUND("시리즈를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    SERIES_GONE("더 이상 존재하지 않는 시리즈입니다.", HttpStatus.GONE),
    SERIES_FORBIDDEN("권한이 없습니다.", HttpStatus.FORBIDDEN),
    SERIES_ALREADY_EXIST("동일한 이름의 시리즈가 이미 존재합니다.", HttpStatus.CONFLICT),
    DEFAULT("시리즈 조작 오류", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus httpStatus;

    SeriesErrorCode(String message, HttpStatus httpStatus) {
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
    public SeriesException exception() {
        return new SeriesException(this);
    }

    @Override
    public SeriesException exception(Throwable cause) {
        return new SeriesException(this, cause);
    }

    @Override
    public RuntimeException exception(Runnable runnable) {
        return new SeriesException(this, runnable);
    }

    @Override
    public RuntimeException exception(Runnable runnable, Throwable cause) {
        return new SeriesException(this, runnable, cause);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload) {
        return new SeriesException(this, payload);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload, Throwable cause) {
        return new SeriesException(this, payload, cause);
    }
}
