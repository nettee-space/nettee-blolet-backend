package nettee.common.image;

import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Supplier;

public enum ImageErrorCode implements ErrorCode {

    UNSUPPORTED_IMAGE_FORMAT("지원하지 않는 이미지 형식입니다.", HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    ;

    private final String message;
    private final HttpStatus httpStatus;

    ImageErrorCode(String message, HttpStatus httpStatus) {
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
    public ImageException exception() {
        return new ImageException(this);
    }

    @Override
    public ImageException exception(Throwable cause) {
        return new ImageException(this, cause);
    }

    @Override
    public ImageException exception(Runnable runnable) {
        return new ImageException(this, runnable);
    }

    @Override
    public ImageException exception(Runnable runnable, Throwable cause) {
        return new ImageException(this, runnable, cause);
    }

    @Override
    public ImageException exception(Supplier<Map<String, Object>> appendPayload) {
        return new ImageException(this, appendPayload);
    }

    @Override
    public ImageException exception(Supplier<Map<String, Object>> appendPayload, Throwable cause) {
        return new ImageException(this, appendPayload, cause);
    }
}
