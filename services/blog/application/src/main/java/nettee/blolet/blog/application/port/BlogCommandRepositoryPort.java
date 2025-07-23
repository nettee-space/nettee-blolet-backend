package nettee.blolet.blog.application.port;

import nettee.blolet.blog.domain.Blog;

import java.util.Optional;

public interface BlogCommandRepositoryPort {
    void transaction(Runnable runnable);

    Blog save(Blog blog);

    void deleteById(String id);

    Optional<Blog> findById(String id);
    int countByUserId(String userId);
    boolean existsById(String id);
}
