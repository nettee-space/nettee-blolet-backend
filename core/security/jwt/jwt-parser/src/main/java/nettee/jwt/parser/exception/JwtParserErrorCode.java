package nettee.jwt.parser.exception;

import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Supplier;

public enum JwtParserErrorCode implements ErrorCode {
    JWT_ACCESS_TOKEN_REQUIRED(HttpStatus.UNAUTHORIZED, "액세스 토큰이 입력되지 않습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    JwtParserErrorCode(HttpStatus httpStatus, String message) {
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
    public JwtParsingException exception() {
        return new JwtParsingException(this);
    }

    @Override
    public JwtParsingException exception(Throwable cause) {
        return new JwtParsingException(this, cause);
    }

    @Override
    public JwtParsingException exception(Runnable runnable) {
        return new JwtParsingException(this, runnable);
    }

    @Override
    public JwtParsingException exception(Runnable runnable, Throwable cause) {
        return new JwtParsingException(this, runnable, cause);
    }

    @Override
    public JwtParsingException exception(Supplier<Map<String, Object>> appendPayload) {
        return new JwtParsingException(this, appendPayload);
    }

    @Override
    public JwtParsingException exception(Supplier<Map<String, Object>> appendPayload, Throwable cause) {
        return new JwtParsingException(this, appendPayload, cause);
    }
}
