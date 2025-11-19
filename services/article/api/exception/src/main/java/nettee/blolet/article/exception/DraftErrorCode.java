package nettee.blolet.article.exception;

import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Supplier;

public enum DraftErrorCode implements ErrorCode {

    // user input
    DRAFT_BLOG_ID_REQUIRED("블로그 ID를 반드시 제공해야 합니다.", HttpStatus.BAD_REQUEST),
    DRAFT_TITLE_MIN_LENGTH("블로그 제목은 반드시 3글자 이상입니다.", HttpStatus.BAD_REQUEST),

    // image status
    DRAFT_IMAGE_SAVE_FAILED("임시글 이미지를 저장하는 데 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    // blog status
    DRAFT_NOT_FOUND("임시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    DRAFT_GONE("더 이상 존재하지 않는 게시물입니다.", HttpStatus.GONE),
    DRAFT_FORBIDDEN("권한이 없습니다.", HttpStatus.FORBIDDEN),
    DRAFT_ALREADY_EXIST("임시글이 이미 존재합니다.", HttpStatus.CONFLICT),
    DEFAULT("임시글 조작 오류", HttpStatus.INTERNAL_SERVER_ERROR),
    BLOG_MISMATCH("드래프트와 시리즈가 같은 블로그에 속하지 않습니다.", HttpStatus.BAD_REQUEST),

    // series status
    SERIES_ARTICLE_NOT_FOUND("시리즈 아티클을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    SERIES_NOT_FOUND("시리즈를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    SERIES_ALREADY_REGISTERED("이미 등록된 시리즈 아티클입니다.", HttpStatus.CONFLICT);

    private final String message;
    private final HttpStatus httpStatus;

    DraftErrorCode(String message, HttpStatus httpStatus) {
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
    public DraftException exception() {
        return new DraftException(this);
    }

    @Override
    public DraftException exception(Throwable cause) {
        return new DraftException(this, cause);
    }

    @Override
    public RuntimeException exception(Runnable runnable) {
        return new DraftException(this, runnable);
    }

    @Override
    public RuntimeException exception(Runnable runnable, Throwable cause) {
        return new DraftException(this, runnable, cause);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload) {
        return new DraftException(this, payload);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload, Throwable cause) {
        return new DraftException(this, payload, cause);
    }
}
