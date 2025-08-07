package nettee.blolet.blog.application.port;

import nettee.blolet.blog.domain.Blog;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface BlogQueryRepositoryPort {
    Optional<Blog> findById(String id);
    Map<String, Blog> findAllByProfileIdIn(Set<String> userIds);
}
