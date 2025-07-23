package nettee.blolet.blog.exception;

import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Supplier;

public enum BlogErrorCode implements ErrorCode {
    BLOG_MAXIMUM_EXCEEDED("사용자당 개설할 수 있는 최대 블로그 개수를 초과했습니다.", HttpStatus.CONFLICT),
    BLOG_NOT_FOUND("블로그를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    BLOG_NAME_CANNOT_BE_BLANK("블로그 이름은 비워 둘 수 없습니다.", HttpStatus.BAD_REQUEST),
    BLOG_COMMAND_FORBIDDEN("이 블로그를 수정하거나 삭제할 수 없습니다.", HttpStatus.FORBIDDEN),
    BLOG_SUSPENDED("일시정지 된 블로그입니다.", HttpStatus.FORBIDDEN),

    // subscription
    ALREADY_SUBSCRIBED_BLOG("이미 구독하였습니다.", HttpStatus.CONFLICT),
    UNSUBSCRIBED_BLOG("이미 구독 취소하거나 아직 구독하지 않은 블로그입니다.", HttpStatus.CONFLICT),
    ALREADY_SUBSCRIBED_BLOG_NEWSLETTER("이미 뉴스레터를 구독하였습니다.", HttpStatus.CONFLICT),
    UNSUBSCRIBED_BLOG_NEWSLETTER("이미 구독 취소하거나 아직 구독하지 않은 뉴스레터입니다.", HttpStatus.CONFLICT),

    DEFAULT("블로그 API 오류", HttpStatus.INTERNAL_SERVER_ERROR),
    ;

    private final String message;
    private final HttpStatus status;

    BlogErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public HttpStatus httpStatus() {
        return status;
    }

    @Override
    public RuntimeException exception() {
        return new BlogException(this);
    }

    @Override
    public RuntimeException exception(Throwable cause) {
        return new BlogException(this, cause);
    }

    @Override
    public RuntimeException exception(Runnable runnable) {
        return new BlogException(this, runnable);
    }

    @Override
    public RuntimeException exception(Runnable runnable, Throwable cause) {
        return new BlogException(this, runnable, cause);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> appendPayload) {
        return new BlogException(this, appendPayload);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> appendPayload, Throwable cause) {
        return new BlogException(this, appendPayload, cause);
    }
}