package nettee.blolet.blog.application.port;

import nettee.blolet.blog.domain.Blog;

public interface BlogCommandPort {
    Blog save(Blog blog);
}
