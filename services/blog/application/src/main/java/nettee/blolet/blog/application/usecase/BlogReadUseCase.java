package nettee.blolet.blog.application.usecase;

import nettee.blolet.blog.domain.Blog;

import java.util.List;

public interface BlogReadUseCase {
    Blog findById(String blogId);
    List<Blog> findByUserProfileIds(List<String> profileIds);
}
