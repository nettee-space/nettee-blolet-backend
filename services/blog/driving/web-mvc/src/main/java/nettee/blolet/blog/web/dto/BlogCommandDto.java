package nettee.blolet.blog.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import nettee.blolet.blog.readmodel.BlogReadModels.BlogDetail;

public final class BlogCommandDto {

    @Builder
    public record BlogUpdateCommand(
            @Schema(description = "블로그 이름", example = "Sun☀️ 好 Moon🌙")
            String name
    ) {}

    @Builder
    public record BlogUpdateResponse(BlogDetail blog) {}

    @Builder
    public record BlogSubscribeResponse(SubscriptionCount counts) {}

    @Builder
    public record BlogUnsubscribeResponse(SubscriptionCount counts) {}

    @Builder
    public record BlogNewsletterSubscribeResponse(SubscriptionCount counts) {}

    @Builder
    public record BlogNewsletterUnsubscribeResponse(SubscriptionCount counts) {}

    @Builder
    public record SubscriptionCount(
            @Schema(description = "이 블로그를 구독한 사용자 수", example = "999")
            Integer totalSubscribers,
            @Schema(description = "사용자가 구독한 블로그 수", example = "99")
            Integer userSubscriptions
    ) {}
}
