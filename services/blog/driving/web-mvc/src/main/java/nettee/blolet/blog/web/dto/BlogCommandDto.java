package nettee.blolet.blog.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import nettee.blolet.blog.application.usecase.subscription.data.SubscriptionStats;
import nettee.blolet.blog.readmodel.BlogReadModels.BlogDetail;

import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_NAME_INVALID_LENGTH;
import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_NAME_REQUIRED;
import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_OWNER_ID_REQUIRED;
import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_URL_INVALID_FORMAT;
import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_URL_INVALID_LENGTH;
import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_URL_REQUIRED;
import static nettee.common.validation.Preconditions.validateLength;
import static nettee.common.validation.Preconditions.validateNotBlank;
import static nettee.common.validation.Preconditions.validateRegex;

public final class BlogCommandDto {

    @Builder
    public record BlogUpdateCommand(
            @Schema(description = "블로그 이름", example = "Sun☀️ 好 Moon🌙")
            String name,
            @Schema(description = "블로그 주소 식별자", example = "wch-os")
            String url
    ) {
        public BlogUpdateCommand {
            validateNotBlank(name, BLOG_NAME_REQUIRED);
            validateNotBlank(url, BLOG_URL_REQUIRED);

            // 앞뒤 공백 문자를 제거 후 유효성 확인
            name = name.strip();
            url = url.strip();

            validateLength(name, 3, 30, BLOG_NAME_INVALID_LENGTH);
            validateRegex(url, "^[A-Za-z0-9_-]+$", BLOG_URL_INVALID_FORMAT);
            validateLength(url, 3, 15, BLOG_URL_INVALID_LENGTH);
        }
    }

    @Builder
    public record BlogUpdateResponse(BlogDetail blog) {}

    @Builder
    public record BlogSubscribeResponse(SubscriptionStats counts) {}

    @Builder
    public record BlogUnsubscribeResponse(SubscriptionStats counts) {}

    @Builder
    public record BlogNewsletterSubscribeResponse(SubscriptionStats counts) {}

    @Builder
    public record BlogNewsletterUnsubscribeResponse(SubscriptionStats counts) {}
}
