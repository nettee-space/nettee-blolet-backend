package nettee.blolet.blog.application.usecase;

import nettee.blolet.blog.domain.Blog;

public interface BlogUpdateUseCase {
    Blog update(Blog blog);
    Blog updateUrl(String userId, String blogId, String url);
}
