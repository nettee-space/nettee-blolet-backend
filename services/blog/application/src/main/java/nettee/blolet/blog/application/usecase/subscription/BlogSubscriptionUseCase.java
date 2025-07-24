package nettee.blolet.blog.application.usecase.subscription;

import nettee.blolet.blog.application.usecase.subscription.data.SubscriptionCount;

public interface BlogSubscriptionUseCase {
    SubscriptionCount subscribeBlog(String userId, String blogId);
}
