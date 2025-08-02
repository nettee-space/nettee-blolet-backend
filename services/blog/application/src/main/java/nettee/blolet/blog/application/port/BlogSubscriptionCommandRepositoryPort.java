package nettee.blolet.blog.application.port;

import nettee.blolet.blog.domain.BlogSubscription;

import java.util.Optional;

public interface BlogSubscriptionCommandRepositoryPort {
    void transaction(Runnable runnable);

    BlogSubscription save(BlogSubscription blog);

    BlogSubscription update(BlogSubscription blog);

    void deleteById(String id);

    Optional<BlogSubscription> findByUserIdAndBlogId(String userId, String blogId);

    int countByUserId(String userId);
    int countByBlogId(String blogId);

    boolean existsByUserIdAndBlogId(String userId, String blogId);
}
