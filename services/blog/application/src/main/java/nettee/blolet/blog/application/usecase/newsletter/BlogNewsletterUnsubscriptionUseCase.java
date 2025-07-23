package nettee.blolet.blog.application.usecase.newsletter;

public interface BlogNewsletterUnsubscriptionUseCase {
    void unsubscribeBlogNewsletter(String username, String blogId);
}
