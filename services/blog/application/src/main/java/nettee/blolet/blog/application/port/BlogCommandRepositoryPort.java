package nettee.blolet.blog.application.port;

import nettee.blolet.blog.domain.Blog;

public interface BlogCommandRepositoryPort {
    void transaction(Runnable runnable);

    Blog save(Blog blog);

    void deleteById(String id);

    int countByUserId(String userId);
    boolean existsById(String id);
}
