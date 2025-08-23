package nettee.common.validation.model.interfaces;

import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Supplier;

public enum ValidationPreparationErrorCode implements ErrorCode {
    UNSUPPORTED_REGEXP_FLAG(
            HttpStatus.INTERNAL_SERVER_ERROR, // 백엔드에서만 입력한다는 전제에서.
            "지원하지 않는 정규 표현식 플래그가 사용되었습니다. 문제가 지속되면 문의해 주세요."
    ),
    INVALID_REGEXP_PATTERN(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "올바른 정규표현식 입력 양식이 아닙니다."
    )
    ;

    private final HttpStatus httpStatus;
    private final String message;

    ValidationPreparationErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
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
    public ValidationPreparationException exception() {
        return new ValidationPreparationException(this);
    }

    @Override
    public ValidationPreparationException exception(Throwable cause) {
        return new ValidationPreparationException(this, cause);
    }

    @Override
    public ValidationPreparationException exception(Runnable runnable) {
        return new ValidationPreparationException(this, runnable);
    }

    @Override
    public ValidationPreparationException exception(Runnable runnable, Throwable cause) {
        return new ValidationPreparationException(this, runnable, cause);
    }

    @Override
    public ValidationPreparationException exception(Supplier<Map<String, Object>> appendPayload) {
        return new ValidationPreparationException(this, appendPayload);
    }

    @Override
    public ValidationPreparationException exception(Supplier<Map<String, Object>> appendPayload, Throwable cause) {
        return new ValidationPreparationException(this, appendPayload, cause);
    }
}
