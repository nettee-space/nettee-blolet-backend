package nettee.common.validation;

import nettee.common.ErrorCode;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

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

    // ╭───────────────────────────────────────╮
    //    collection: validateLength variants
    // ╰───────────────────────────────────────╯

    public static void validateLength(
            Collection<?> collection,
            int min,
            int max,
            ErrorCode errorCode
    ) {
        performLengthValidation(collection, min, max, errorCode::exception);
    }

    public static void validateLength(
            Collection<?> collection,
            int min,
            int max,
            ErrorCode errorCode,
            Throwable cause
    ) {
        performLengthValidation(collection, min, max, () -> errorCode.exception(cause));
    }

    public static void validateLength(
            Collection<?> collection,
            int min,
            int max,
            ErrorCode errorCode,
            Runnable runnable
    ) {
        performLengthValidation(collection, min, max, () -> errorCode.exception(runnable));
    }

    public static void validateLength(
            Collection<?> collection,
            int min,
            int max,
            ErrorCode errorCode,
            Runnable runnable,
            Throwable cause
    ) {
        performLengthValidation(collection, min, max, () -> errorCode.exception(runnable, cause));
    }

    public static void validateLength(
            Collection<?> collection,
            int min,
            int max,
            ErrorCode errorCode,
            Supplier<Map<String, Object>> payloadSupplier
    ) {
        performLengthValidation(collection, min, max, () -> errorCode.exception(payloadSupplier));
    }

    public static void validateLength(
            Collection<?> collection,
            int min,
            int max,
            ErrorCode errorCode,
            Supplier<Map<String, Object>> payloadSupplier,
            Throwable cause
    ) {
        performLengthValidation(collection, min, max, () -> errorCode.exception(payloadSupplier, cause));
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

    // ╭────────────────────────────────────╮
    //    collection: validateMin variants
    // ╰────────────────────────────────────╯

    public static void validateMin(
            Collection<?> collection,
            int min,
            ErrorCode errorCode
    ) {
        performMinValidation(collection, min, errorCode::exception);
    }

    public static void validateMin(
            Collection<?> collection,
            int min,
            ErrorCode errorCode,
            Throwable cause
    ) {
        performMinValidation(collection, min, () -> errorCode.exception(cause));
    }

    public static void validateMin(
            Collection<?> collection,
            int min,
            ErrorCode errorCode,
            Runnable runnable
    ) {
        performMinValidation(collection, min, () -> errorCode.exception(runnable));
    }

    public static void validateMin(
            Collection<?> collection,
            int min,
            ErrorCode errorCode,
            Runnable runnable,
            Throwable cause
    ) {
        performMinValidation(collection, min, () -> errorCode.exception(runnable, cause));
    }

    public static void validateMin(
            Collection<?> collection,
            int min,
            ErrorCode errorCode,
            Supplier<Map<String, Object>> payloadSupplier
    ) {
        performMinValidation(collection, min, () -> errorCode.exception(payloadSupplier));
    }

    public static void validateMin(
            Collection<?> collection,
            int min,
            ErrorCode errorCode,
            Supplier<Map<String, Object>> payloadSupplier,
            Throwable cause
    ) {
        performMinValidation(collection, min, () -> errorCode.exception(payloadSupplier, cause));
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

    // ╭────────────────────────────────────╮
    //    collection: validateMax variants
    // ╰────────────────────────────────────╯

    public static void validateMax(
            Collection<?> collection,
            int max,
            ErrorCode errorCode
    ) {
        performMaxValidation(collection, max, errorCode::exception);
    }

    public static void validateMax(
            Collection<?> collection,
            int max,
            ErrorCode errorCode,
            Throwable cause
    ) {
        performMaxValidation(collection, max, () -> errorCode.exception(cause));
    }

    public static void validateMax(
            Collection<?> collection,
            int max,
            ErrorCode errorCode,
            Runnable runnable
    ) {
        performMaxValidation(collection, max, () -> errorCode.exception(runnable));
    }

    public static void validateMax(
            Collection<?> collection,
            int max,
            ErrorCode errorCode,
            Runnable runnable,
            Throwable cause
    ) {
        performMaxValidation(collection, max, () -> errorCode.exception(runnable, cause));
    }

    public static void validateMax(
            Collection<?> collection,
            int max,
            ErrorCode errorCode,
            Supplier<Map<String, Object>> payloadSupplier
    ) {
        performMaxValidation(collection, max, () -> errorCode.exception(payloadSupplier));
    }

    public static void validateMax(
            Collection<?> collection,
            int max,
            ErrorCode errorCode,
            Supplier<Map<String, Object>> payloadSupplier,
            Throwable cause
    ) {
        performMaxValidation(collection, max, () -> errorCode.exception(payloadSupplier, cause));
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

    private static void performLengthValidation(
            Collection<?> collection,
            int min,
            int max,
            Supplier<? extends RuntimeException> exceptionSupplier
    ) {
        validateLengthArgument(collection, min, max);

        if (collection == null) {
            if (min == 0) {
                return; // 허용: min이 0이면 null도 OK
            }
            throw new NullPointerException("Collection must not be null if min is not 0.");
        }

        if (collection.isEmpty() && min == 0) {
            return;
        }

        if (collection.size() < min || collection.size() > max) {
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

    private static void performMinValidation(
            Collection<?> collection,
            int min,
            Supplier<? extends RuntimeException> exceptionSupplier
    ) {
        validateMinArgument(collection, min);

        if (collection == null) {
            if (min == 0) {
                return; // 허용: min이 0이면 null도 OK
            }
            throw new NullPointerException("Collection must not be null if min is not 0.");
        }

        if (collection.isEmpty() && min == 0) {
            return; // 빈 컬렉션도 min == 0이면 허용
        }

        if (collection.size() < min) {
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

    private static void performMaxValidation(
            Collection<?> collection,
            int max,
            Supplier<? extends RuntimeException> exceptionSupplier
    ) {
        validateMaxArgument(max);

        if (collection == null) {
            return; // null은 크기 0으로 취급
        }

        if (collection.isEmpty()) {
            return; // 항상 <= max (where max >= 0)
        }

        if (collection.size() > max) {
            throw exceptionSupplier.get();
        }
    }

    private static void performRegexValidation(
            String value,
            String regexp,
            Supplier<? extends RuntimeException> exceptionSupplier
    ) {
        assert regexp != null : "Pattern must not be null.";
        Pattern pattern = compileRegex(regexp);

        if (!pattern.matcher(value).matches()) {
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

    private static void validateLengthArgument(Collection<?> collection, int min, int max) {
        assert min >= 0 && max >= 0 : "min, max cannot be less than or equal to 0";
        assert min < max : "max must be greater than or equal to " + min;

        if (collection == null && min != 0) {
            throw new NullPointerException("Collection must not be null if min is not 0.");
        }
    }

    private static void validateMinArgument(String value, int min) {
        assert min >= 0 : "min cannot be less than 0";

        if (value == null && min != 0) {
            throw new NullPointerException("String must not be null if min is not 0.");
        }
    }

    private static void validateMinArgument(Collection<?> collection, int min) {
        assert min >= 0 : "min cannot be less than 0";

        if (collection == null && min != 0) {
            throw new NullPointerException("Collection must not be null if min is not 0.");
        }
    }

    private static void validateMaxArgument(int max) {
        assert max >= 0 : "max cannot be less than 0";
    }

    private static Pattern compileRegex(String regex) {
        try {
            return Pattern.compile(regex);
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Invalid regex: " + regex, e);
        }
    }
}
