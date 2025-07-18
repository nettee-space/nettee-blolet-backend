package nettee.blolet.blog.exception;

import nettee.common.CustomException;
import nettee.common.ErrorCode;

import java.util.Map;
import java.util.function.Supplier;

public class BlogException extends CustomException {
    public BlogException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BlogException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public BlogException(ErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public BlogException(ErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public BlogException(ErrorCode errorCode, Supplier<Map<String, Object>> payloadSupplier) {
        super(errorCode, payloadSupplier);
    }

    public BlogException(ErrorCode errorCode, Supplier<Map<String, Object>> payloadSupplier, Throwable cause) {
        super(errorCode, payloadSupplier, cause);
    }

    public BlogException() {
        super();
    }

    public BlogException(Throwable cause) {
        super(cause);
    }

    public BlogException(Runnable runnable) {
        super(runnable);
    }

    public BlogException(Runnable runnable, Throwable cause) {
        super(runnable, cause);
    }

    public BlogException(Supplier<Map<String, Object>> payloadSupplier) {
        super(payloadSupplier);
    }

    public BlogException(Supplier<Map<String, Object>> payloadSupplier, Throwable cause) {
        super(payloadSupplier, cause);
    }
}