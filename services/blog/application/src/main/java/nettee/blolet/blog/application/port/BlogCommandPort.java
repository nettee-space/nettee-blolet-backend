package nettee.blolet.blog.application.port;

import nettee.blolet.blog.domain.Blog;

public interface BlogCommandPort {
    void transaction(Runnable runnable);

    Blog save(Blog blog);
}
