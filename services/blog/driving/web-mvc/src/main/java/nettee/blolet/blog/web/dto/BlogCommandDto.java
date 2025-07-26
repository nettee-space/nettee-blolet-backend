package nettee.blolet.blog.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import nettee.blolet.blog.application.usecase.subscription.data.SubscriptionStats;
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
    public record BlogSubscribeResponse(SubscriptionStats counts) {}

    @Builder
    public record BlogUnsubscribeResponse(SubscriptionStats counts) {}

    @Builder
    public record BlogNewsletterSubscribeResponse(SubscriptionStats counts) {}

    @Builder
    public record BlogNewsletterUnsubscribeResponse(SubscriptionStats counts) {}
}
