package nettee.blolet.blog.application.usecase;

import nettee.blolet.blog.domain.Blog;
import nettee.blolet.blog.readmodel.BlogReadModels.UserProfileBlogs;

import java.util.Map;
import java.util.Set;

public interface BlogReadUseCase {
    Blog findById(String blogId);
    Map<String, UserProfileBlogs> findByUserProfileIds(Set<String> profileIds);
}
