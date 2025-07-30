package nettee.blolet.blog.application.usecase.subscription;

import nettee.blolet.blog.application.usecase.subscription.data.SubscriptionStats;

public interface BlogUnsubscriptionUseCase {
    SubscriptionStats unsubscribeBlog(String username, String blogId);
}
