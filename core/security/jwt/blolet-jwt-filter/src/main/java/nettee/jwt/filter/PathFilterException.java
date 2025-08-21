package nettee.jwt.filter;

import nettee.common.CustomException;
import nettee.common.ErrorCode;

import java.util.Map;
import java.util.function.Supplier;

public class PathFilterException extends CustomException {
    public PathFilterException(ErrorCode errorCode) {
        super(errorCode);
    }

    public PathFilterException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public PathFilterException(ErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public PathFilterException(ErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public PathFilterException(ErrorCode errorCode, Supplier<Map<String, Object>> payloadSupplier) {
        super(errorCode, payloadSupplier);
    }

    public PathFilterException(ErrorCode errorCode, Supplier<Map<String, Object>> payloadSupplier, Throwable cause) {
        super(errorCode, payloadSupplier, cause);
    }

    public PathFilterException() {
        super();
    }

    public PathFilterException(Throwable cause) {
        super(cause);
    }

    public PathFilterException(Runnable runnable) {
        super(runnable);
    }

    public PathFilterException(Runnable runnable, Throwable cause) {
        super(runnable, cause);
    }

    public PathFilterException(Supplier<Map<String, Object>> payloadSupplier) {
        super(payloadSupplier);
    }

    public PathFilterException(Supplier<Map<String, Object>> payloadSupplier, Throwable cause) {
        super(payloadSupplier, cause);
    }
}
