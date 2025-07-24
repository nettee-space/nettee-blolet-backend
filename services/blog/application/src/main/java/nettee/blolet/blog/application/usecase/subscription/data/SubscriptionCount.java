package nettee.blolet.blog.application.usecase.subscription.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record SubscriptionCount(
        @Schema(description = "이 블로그를 구독한 사용자 수", example = "999")
        Integer totalSubscribers,
        @Schema(description = "사용자가 구독한 블로그 수", example = "99")
        Integer userSubscriptions
) {}