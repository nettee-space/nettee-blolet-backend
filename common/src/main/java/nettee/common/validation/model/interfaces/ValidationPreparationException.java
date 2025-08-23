package nettee.common.validation.model.interfaces;

import nettee.common.CustomException;
import nettee.common.ErrorCode;

import java.util.Map;
import java.util.function.Supplier;

public class ValidationPreparationException extends CustomException {
    public ValidationPreparationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ValidationPreparationException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public ValidationPreparationException(ErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public ValidationPreparationException(ErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public ValidationPreparationException(ErrorCode errorCode, Supplier<Map<String, Object>> payloadSupplier) {
        super(errorCode, payloadSupplier);
    }

    public ValidationPreparationException(ErrorCode errorCode, Supplier<Map<String, Object>> payloadSupplier, Throwable cause) {
        super(errorCode, payloadSupplier, cause);
    }

    public ValidationPreparationException() {
        super();
    }

    public ValidationPreparationException(Throwable cause) {
        super(cause);
    }

    public ValidationPreparationException(Runnable runnable) {
        super(runnable);
    }

    public ValidationPreparationException(Runnable runnable, Throwable cause) {
        super(runnable, cause);
    }

    public ValidationPreparationException(Supplier<Map<String, Object>> payloadSupplier) {
        super(payloadSupplier);
    }

    public ValidationPreparationException(Supplier<Map<String, Object>> payloadSupplier, Throwable cause) {
        super(payloadSupplier, cause);
    }
}
