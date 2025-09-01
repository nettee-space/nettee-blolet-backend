package nettee.jwt.parser.exception;

import nettee.common.CustomException;
import nettee.common.ErrorCode;

import java.util.Map;
import java.util.function.Supplier;

public class JwtParsingException extends CustomException {
    public JwtParsingException(ErrorCode errorCode) {
        super(errorCode);
    }

    public JwtParsingException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public JwtParsingException(ErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public JwtParsingException(ErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public JwtParsingException(ErrorCode errorCode, Supplier<Map<String, Object>> payloadSupplier) {
        super(errorCode, payloadSupplier);
    }

    public JwtParsingException(ErrorCode errorCode, Supplier<Map<String, Object>> payloadSupplier, Throwable cause) {
        super(errorCode, payloadSupplier, cause);
    }

    public JwtParsingException() {
        super();
    }

    public JwtParsingException(Throwable cause) {
        super(cause);
    }

    public JwtParsingException(Runnable runnable) {
        super(runnable);
    }

    public JwtParsingException(Runnable runnable, Throwable cause) {
        super(runnable, cause);
    }

    public JwtParsingException(Supplier<Map<String, Object>> payloadSupplier) {
        super(payloadSupplier);
    }

    public JwtParsingException(Supplier<Map<String, Object>> payloadSupplier, Throwable cause) {
        super(payloadSupplier, cause);
    }
}
