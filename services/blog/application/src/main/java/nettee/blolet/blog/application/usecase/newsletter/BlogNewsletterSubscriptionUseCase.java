package nettee.blolet.blog.application.usecase.newsletter;

import nettee.blolet.blog.application.usecase.subscription.data.SubscriptionStats;

public interface BlogNewsletterSubscriptionUseCase {
    SubscriptionStats subscribeBlogNewsletter(String userId, String blogId);
}
