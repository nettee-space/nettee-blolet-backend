package nettee.blolet.blog.application.usecase.newsletter;

import nettee.blolet.blog.application.usecase.subscription.data.SubscriptionStats;

public interface BlogNewsletterUnsubscriptionUseCase {
    SubscriptionStats unsubscribeBlogNewsletter(String userId, String blogId);
}
