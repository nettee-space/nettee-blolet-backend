package nettee.blolet.blog.application.usecase;

import nettee.blolet.blog.domain.Blog;

public interface BlogUpdateUseCase {
    Blog update(String blogId, String name, String url);
    Blog updateUrl(String blogId, String url);
}
