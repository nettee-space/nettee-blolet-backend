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

    // ╭─────────────────────────────╮
    //    validateNotBlank variants
    // ╰─────────────────────────────╯

    public static void validateNotBlank(String value, ErrorCode errorCode) {
        if (isBlank(value)) {
            throw errorCode.exception();
        }
    }

    public static void validateNotBlank(String value, ErrorCode errorCode, Throwable cause) {
        if (isBlank(value)) {
            throw errorCode.exception(cause);
        }
    }

    public static void validateNotBlank(String value, ErrorCode errorCode, Runnable runnable) {
        if (isBlank(value)) {
            throw errorCode.exception(runnable);
        }
    }

    public static void validateNotBlank(
            String value,
            ErrorCode errorCode,
            Runnable runnable,
            Throwable cause
    ) {
        if (isBlank(value)) {
            throw errorCode.exception(runnable, cause);
        }
    }

    public static void validateNotBlank(
            String value,
            ErrorCode errorCode,
            Supplier<Map<String, Object>> payloadSupplier
    ) {
        if (isBlank(value)) {
            throw errorCode.exception(payloadSupplier);
        }
    }

    public static void validateNotBlank(
            String value,
            ErrorCode errorCode,
            Supplier<Map<String, Object>> payloadSupplier,
            Throwable cause
    ) {
        if (isBlank(value)) {
            throw errorCode.exception(payloadSupplier, cause);
        }
    }

    // ╭───────────────────────────────────╮
    //    String: validateLength variants
    // ╰───────────────────────────────────╯

    public static void validateLength(
            String value,
            int min,
            int max,
            ErrorCode errorCode
    ) {
        performLengthValidation(value, min, max, errorCode::exception);
    }

    public static void validateLength(
            String value,
            int min,
            int max,
            ErrorCode errorCode,
            Throwable cause
    ) {
        performLengthValidation(value, min, max, () -> errorCode.exception(cause));
    }

    public static void validateLength(
            String value,
            int min,
            int max,
            ErrorCode errorCode,
            Runnable runnable
    ) {
        performLengthValidation(value, min, max, () -> errorCode.exception(runnable));
    }

    public static void validateLength(
            String value,
            int min,
            int max,
            ErrorCode errorCode,
            Runnable runnable,
            Throwable cause
    ) {
        performLengthValidation(value, min, max, () -> errorCode.exception(runnable, cause));
    }

    public static void validateLength(
            String value,
            int min,
            int max,
            ErrorCode errorCode,
            Supplier<Map<String, Object>> payloadSupplier
    ) {
        performLengthValidation(value, min, max, () -> errorCode.exception(payloadSupplier));
    }

    public static void validateLength(
            String value,
            int min,
            int max,
            ErrorCode errorCode,
            Supplier<Map<String, Object>> payloadSupplier,
            Throwable cause
    ) {
        performLengthValidation(value, min, max, () -> errorCode.exception(payloadSupplier, cause));
    }

    // ╭────────────────────────────────╮
    //    String: validateMin variants
    // ╰────────────────────────────────╯

    public static void validateMin(
            String value,
            int min,
            ErrorCode errorCode
    ) {
        performMinValidation(value, min, errorCode::exception);
    }

    public static void validateMin(
            String value,
            int min,
            ErrorCode errorCode,
            Throwable cause
    ) {
        performMinValidation(value, min, () -> errorCode.exception(cause));
    }

    public static void validateMin(
            String value,
            int min,
            ErrorCode errorCode,
            Runnable runnable
    ) {
        performMinValidation(value, min, () -> errorCode.exception(runnable));
    }

    public static void validateMin(
            String value,
            int min,
            ErrorCode errorCode,
            Runnable runnable,
            Throwable cause
    ) {
        performMinValidation(value, min, () -> errorCode.exception(runnable, cause));
    }

    public static void validateMin(
            String value,
            int min,
            ErrorCode errorCode,
            Supplier<Map<String, Object>> payloadSupplier
    ) {
        performMinValidation(value, min, () -> errorCode.exception(payloadSupplier));
    }

    public static void validateMin(
            String value,
            int min,
            ErrorCode errorCode,
            Supplier<Map<String, Object>> payloadSupplier,
            Throwable cause
    ) {
        performMinValidation(value, min, () -> errorCode.exception(payloadSupplier, cause));
    }

    // ╭────────────────────────────────╮
    //    String: validateMax variants
    // ╰────────────────────────────────╯

    public static void validateMax(
            String value,
            int max,
            ErrorCode errorCode
    ) {
        performMaxValidation(value, max, errorCode::exception);
    }

    public static void validateMax(
            String value,
            int max,
            ErrorCode errorCode,
            Throwable cause
    ) {
        performMaxValidation(value, max, () -> errorCode.exception(cause));
    }

    public static void validateMax(
            String value,
            int max,
            ErrorCode errorCode,
            Runnable runnable
    ) {
        performMaxValidation(value, max, () -> errorCode.exception(runnable));
    }

    public static void validateMax(
            String value,
            int max,
            ErrorCode errorCode,
            Runnable runnable,
            Throwable cause
    ) {
        performMaxValidation(value, max, () -> errorCode.exception(runnable, cause));
    }

    public static void validateMax(
            String value,
            int max,
            ErrorCode errorCode,
            Supplier<Map<String, Object>> payloadSupplier
    ) {
        performMaxValidation(value, max, () -> errorCode.exception(payloadSupplier));
    }

    public static void validateMax(
            String value,
            int max,
            ErrorCode errorCode,
            Supplier<Map<String, Object>> payloadSupplier,
            Throwable cause
    ) {
        performMaxValidation(value, max, () -> errorCode.exception(payloadSupplier, cause));
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

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    private static void performLengthValidation(
            String value,
            int min,
            int max,
            Supplier<? extends RuntimeException> exceptionSupplier
    ) {
        validateLengthArgument(value, min, max);

        if (value == null) {
            if (min == 0) {
                return; // null 허용: min이 0이면
            }
            throw new NullPointerException("String must not be null if min is not 0.");
        }

        if (value.isEmpty() && min == 0) {
            return; // 빈 문자열도 min == 0이면 허용
        }

        int len = value.length();
        if (len < min || len > max) {
            throw exceptionSupplier.get();
        }
    }

    private static void performMinValidation(
            String value,
            int min,
            Supplier<? extends RuntimeException> exceptionSupplier
    ) {
        validateMinArgument(value, min);

        if (value == null) {
            if (min == 0) {
                return; // null 허용: min이 0이면
            }
            throw new NullPointerException("String must not be null if min is not 0.");
        }

        if (value.isEmpty() && min == 0) {
            return;
        }

        if (value.length() < min) {
            throw exceptionSupplier.get();
        }
    }

    private static void performMaxValidation(
            String value,
            int max,
            Supplier<? extends RuntimeException> exceptionSupplier
    ) {
        validateMaxArgument(max);

        if (value == null) {
            return; // null은 길이 0으로 보고 항상 허용 (max >= 0 전제)
        }

        if (value.isEmpty()) {
            return; // 길이 0 <= max
        }

        if (value.length() > max) {
            throw exceptionSupplier.get();
        }
    }

    private static void validateLengthArgument(String value, int min, int max) {
        assert min >= 0 : "min cannot be less than 0";
        assert max >= 0 : "max cannot be less than 0";
        assert min <= max : "max must be greater than or equal to " + min;

        if (value == null && min != 0) {
            throw new NullPointerException("String must not be null if min is not 0.");
        }
    }

    private static void validateMinArgument(String value, int min) {
        assert min >= 0 : "min cannot be less than 0";

        if (value == null && min != 0) {
            throw new NullPointerException("String must not be null if min is not 0.");
        }
    }

    private static void validateMaxArgument(int max) {
        assert max >= 0 : "max cannot be less than 0";
    }
}
