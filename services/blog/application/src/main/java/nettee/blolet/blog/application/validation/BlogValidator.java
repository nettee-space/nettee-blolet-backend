package nettee.blolet.blog.application.validation;

import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_ID_REQUIRED;
import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_NAME_INVALID_LENGTH;
import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_NAME_REQUIRED;
import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_NICKNAME_REQUIRED;
import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_OWNER_ID_REQUIRED;
import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_PROFILE_ID_REQUIRED;
import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_URL_INVALID_FORMAT;
import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_URL_INVALID_LENGTH;
import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_URL_REQUIRED;
import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_USERNAME_REQUIRED;
import static nettee.common.validation.Preconditions.validateLength;
import static nettee.common.validation.Preconditions.validateNotBlank;
import static nettee.common.validation.Preconditions.validateNotNull;
import static nettee.common.validation.Preconditions.validateRegex;

public final class BlogValidator {

    private BlogValidator() {}

    public static void validate(BlogValidationTarget field, Object value) {
        switch (field) {
            case BLOG_ID ->
                validateNotNull(value, BLOG_ID_REQUIRED);
            case BLOG_USER_ID -> {
                String str = castToString(value);
                validateNotBlank(str, BLOG_OWNER_ID_REQUIRED);
            }
            case BLOG_PROFILE_ID -> {
                String str = castToString(value);
                validateNotBlank(str, BLOG_PROFILE_ID_REQUIRED);
            }
            case BLOG_NAME -> {
                String str = castToString(value);
                validateNotBlank(str, BLOG_NAME_REQUIRED);

                str = str.strip();
                validateLength(str, 3, 30, BLOG_NAME_INVALID_LENGTH);
            }
            case BLOG_URL_IDENTIFIER -> {
                String str = castToString(value);
                validateNotBlank(str, BLOG_URL_REQUIRED);

                str = str.strip();
                validateRegex(str, "^[A-Za-z0-9_-]+$", BLOG_URL_INVALID_FORMAT);
                validateLength(str, 3, 15, BLOG_URL_INVALID_LENGTH);
            }
            case BLOG_USERNAME -> {
                String str = castToString(value);
                validateNotBlank(str, BLOG_USERNAME_REQUIRED);
            }
            case BLOG_NICKNAME -> {
                String str = castToString(value);
                validateNotBlank(str, BLOG_NICKNAME_REQUIRED);
            }
        }
    }

    private static String castToString(Object value) {
        assert value == null || value instanceof String :
                "value must be a string or null but is " + value.getClass();
        return (String) value;
    }

    public enum BlogValidationTarget {
        BLOG_ID,
        BLOG_USER_ID,
        BLOG_PROFILE_ID,
        BLOG_NAME,
        BLOG_URL_IDENTIFIER,
        BLOG_USERNAME,
        BLOG_NICKNAME
    }
}
