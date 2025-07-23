package nettee.blolet.blog.application.usecase.subscription;

public interface BlogUnsubscriptionUseCase {
    void unsubscribeBlog(String username, String blogId);
}
