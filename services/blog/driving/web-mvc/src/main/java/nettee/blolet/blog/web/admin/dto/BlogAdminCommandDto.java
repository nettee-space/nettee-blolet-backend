package nettee.blolet.blog.web.admin.dto;

import lombok.Builder;
import nettee.blolet.blog.readmodel.BlogReadModels.BlogDetail;

import static nettee.blolet.blog.api.validation.BlogValidator.BlogValidationTarget.BLOG_NAME;
import static nettee.blolet.blog.api.validation.BlogValidator.BlogValidationTarget.BLOG_NICKNAME;
import static nettee.blolet.blog.api.validation.BlogValidator.BlogValidationTarget.BLOG_PROFILE_ID;
import static nettee.blolet.blog.api.validation.BlogValidator.BlogValidationTarget.BLOG_URL_IDENTIFIER;
import static nettee.blolet.blog.api.validation.BlogValidator.BlogValidationTarget.BLOG_USERNAME;
import static nettee.blolet.blog.api.validation.BlogValidator.BlogValidationTarget.BLOG_USER_ID;
import static nettee.blolet.blog.api.validation.BlogValidator.validate;

public final class BlogAdminCommandDto {
    private BlogAdminCommandDto() {}

    @Builder
    public record BlogCreateCommand(
            String profileId,
            String name,
            String url,
            String username,
            String nickname
    ) {
        public BlogCreateCommand {
            validate(BLOG_NAME, name);
            validate(BLOG_URL_IDENTIFIER, url);
            validate(BLOG_USERNAME, username);
            validate(BLOG_NICKNAME, nickname);
        }
    }

    @Builder
    public record BlogInternalCreateCommand(
            String userId,
            String profileId,
            String name,
            String url,
            String username,
            String nickname
    ) {
        public BlogInternalCreateCommand {
            validate(BLOG_USER_ID, userId);
            validate(BLOG_PROFILE_ID, profileId);
            validate(BLOG_USERNAME, username);
            validate(BLOG_NICKNAME, nickname);
            validate(BLOG_NAME, name);
            validate(BLOG_URL_IDENTIFIER, url);
        }
    }

    @Builder
    public record BlogCreateResponse(
            BlogDetail blog
    ) {}
}
