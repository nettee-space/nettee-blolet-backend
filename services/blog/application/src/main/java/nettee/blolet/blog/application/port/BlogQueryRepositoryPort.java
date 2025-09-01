package nettee.blolet.blog.application.port;

import nettee.blolet.blog.domain.Blog;
import nettee.blolet.blog.readmodel.BlogReadModels.UserProfileBlogs;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface BlogQueryRepositoryPort {
    Optional<Blog> findById(String id);
    Map<String, UserProfileBlogs> findAllByProfileIdIn(Set<String> userIds);
    Set<String> findBlogIdsByUserId(String userId);
}
