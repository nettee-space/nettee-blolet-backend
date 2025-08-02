package nettee.common.validation;

import nettee.common.ErrorCode;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

public final class Preconditions {
    private Preconditions() {}

    // ╭────────────────────────────╮
    //    validateNotNull variants
    // ╰────────────────────────────╯

    public static void validateNotNull(Object value, ErrorCode errorCode) {
        if (value == null) {
            throw errorCode.exception();
        }
    }

    public static void validateNotNull(Object value, ErrorCode errorCode, Throwable cause) {
        if (value == null) {
            throw errorCode.exception(cause);
        }
    }

    public static void validateNotNull(Object value, ErrorCode errorCode, Runnable runnable) {
        if (value == null) {
            throw errorCode.exception(runnable);
        }
    }

    public static void validateNotNull(
            Object value,
            ErrorCode errorCode,
            Runnable runnable,
            Throwable cause
    ) {
        if (value == null) {
            throw errorCode.exception(runnable, cause);
        }
    }

    public static void validateNotNull(
            Object value,
            ErrorCode errorCode,
            Supplier<Map<String, Object>> payloadSupplier
    ) {
        if (value == null) {
            throw errorCode.exception(payloadSupplier);
        }
    }

    public static void validateNotNull(
            Object value,
            ErrorCode errorCode,
            Supplier<Map<String, Object>> payloadSupplier,
            Throwable cause
    ) {
        if (value == null) {
            throw errorCode.exception(payloadSupplier, cause);
        }
    }

    // ╭─────────────────────────────────────╮
    //    String: validateNotEmpty variants
    // ╰─────────────────────────────────────╯

    public static void validateNotEmpty(String value, ErrorCode errorCode) {
        if (isEmpty(value)) {
            throw errorCode.exception();
        }
    }

    public static void validateNotEmpty(String value, ErrorCode errorCode, Throwable cause) {
        if (isEmpty(value)) {
            throw errorCode.exception(cause);
        }
    }

    public static void validateNotEmpty(String value, ErrorCode errorCode, Runnable runnable) {
        if (isEmpty(value)) {
            throw errorCode.exception(runnable);
        }
    }

    public static void validateNotEmpty(
            String value,
            ErrorCode errorCode,
            Runnable runnable,
            Throwable cause
    ) {
        if (isEmpty(value)) {
            throw errorCode.exception(runnable, cause);
        }
    }

    public static void validateNotEmpty(
            String value,
            ErrorCode errorCode,
            Supplier<Map<String, Object>> payloadSupplier
    ) {
        if (isEmpty(value)) {
            throw errorCode.exception(payloadSupplier);
        }
    }

    public static void validateNotEmpty(
            String value,
            ErrorCode errorCode,
            Supplier<Map<String, Object>> payloadSupplier,
            Throwable cause
    ) {
        if (isEmpty(value)) {
            throw errorCode.exception(payloadSupplier, cause);
        }
    }

    // ╭─────────────────────────────────────────╮
    //    Collection: validateNotEmpty variants
    // ╰─────────────────────────────────────────╯

    public static void validateNotEmpty(Collection<?> value, ErrorCode errorCode) {
        if (isEmpty(value)) {
            throw errorCode.exception();
        }
    }

    public static void validateNotEmpty(Collection<?> value, ErrorCode errorCode, Throwable cause) {
        if (isEmpty(value)) {
            throw errorCode.exception(cause);
        }
    }

    public static void validateNotEmpty(Collection<?> value, ErrorCode errorCode, Runnable runnable) {
        if (isEmpty(value)) {
            throw errorCode.exception(runnable);
        }
    }

    public static void validateNotEmpty(
            Collection<?> value,
            ErrorCode errorCode,
            Runnable runnable,
            Throwable cause
    ) {
        if (isEmpty(value)) {
            throw errorCode.exception(runnable, cause);
        }
    }

    public static void validateNotEmpty(
            Collection<?> value,
            ErrorCode errorCode,
            Supplier<Map<String, Object>> payloadSupplier
    ) {
        if (isEmpty(value)) {
            throw errorCode.exception(payloadSupplier);
        }
    }

    public static void validateNotEmpty(
            Collection<?> value,
            ErrorCode errorCode,
            Supplier<Map<String, Object>> payloadSupplier,
            Throwable cause
    ) {
        if (isEmpty(value)) {
            throw errorCode.exception(payloadSupplier, cause);
        }
    }

    // ╭──────────────────╮
    //    Helper Methods
    // ╰──────────────────╯

    private static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    private static boolean isEmpty(Collection<?> value) {
        return value == null || value.isEmpty();
    }
}
