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
            case ID ->
                validateNotNull(value, BLOG_ID_REQUIRED);
            case USER_ID -> {
                String str = (String) value;
                validateNotBlank(str, BLOG_OWNER_ID_REQUIRED);
            }
            case PROFILE_ID -> {
                String str = (String) value;
                validateNotBlank(str, BLOG_PROFILE_ID_REQUIRED);
            }
            case NAME -> {
                String str = (String) value;
                validateNotBlank(str, BLOG_NAME_REQUIRED);

                // 앞뒤 공백 문자를 제거 후 유효성 확인
                str = str.strip();
                validateLength(str, 3, 30, BLOG_NAME_INVALID_LENGTH);
            }
            case URL_IDENTIFIER -> {
                String str = (String) value;
                validateNotBlank(str, BLOG_URL_REQUIRED);

                // 앞뒤 공백 문자를 제거 후 유효성 확인
                str = str.strip();
                validateRegex(str, "^[A-Za-z0-9_-]+$", BLOG_URL_INVALID_FORMAT);
                validateLength(str, 3, 15, BLOG_URL_INVALID_LENGTH);
            }
            case USERNAME -> {
                String str = (String) value;
                validateNotBlank(str, BLOG_USERNAME_REQUIRED);
            }
            case NICKNAME -> {
                String str = (String) value;
                validateNotBlank(str, BLOG_NICKNAME_REQUIRED);
            }
        }
    }

    public enum BlogValidationTarget {
        ID,
        USER_ID,
        PROFILE_ID,
        NAME,
        URL_IDENTIFIER,
        USERNAME,
        NICKNAME
    }
}
