package nettee.blolet.blog.rdb;

import nettee.blolet.blog.application.port.BlogSubscriptionCommandRepositoryPort;
import nettee.blolet.blog.domain.BlogSubscription;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RdbBlogSubscriptionCommandRepositoryAdapter implements BlogSubscriptionCommandRepositoryPort {
    @Override
    public void transaction(Runnable runnable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BlogSubscription save(BlogSubscription blog) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BlogSubscription update(BlogSubscription blog) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteById(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<BlogSubscription> findByUserIdAndBlogId(String userId, String blogId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int countByUserId(String userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int countByBlogId(String blogId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean existsByUserIdAndBlogId(String userId, String blogId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
