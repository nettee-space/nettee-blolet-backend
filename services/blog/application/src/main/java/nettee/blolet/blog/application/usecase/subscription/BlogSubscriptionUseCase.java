package nettee.blolet.blog.application.usecase.subscription;

import nettee.blolet.blog.application.usecase.subscription.data.SubscriptionStats;

public interface BlogSubscriptionUseCase {
    SubscriptionStats subscribeBlog(String userId, String blogId);
}
