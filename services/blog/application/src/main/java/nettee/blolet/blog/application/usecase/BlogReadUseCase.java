package nettee.blolet.blog.application.usecase;

import nettee.blolet.blog.domain.Blog;

import java.util.Map;
import java.util.Set;

public interface BlogReadUseCase {
    Blog findById(String blogId);
    Map<String, Blog> findByUserProfileIds(Set<String> profileIds);
}
