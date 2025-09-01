package nettee.blolet.blog.rdb;

import lombok.RequiredArgsConstructor;
import nettee.blolet.blog.application.port.BlogQueryRepositoryPort;
import nettee.blolet.blog.domain.Blog;
import nettee.blolet.blog.rdb.mapper.BlogEntityMapper;
import nettee.blolet.blog.rdb.repository.BlogQueryJpaRepository;
import nettee.blolet.blog.rdb.repository.projection.BlogQueryProjection.BlogIdProjection;
import nettee.blolet.blog.readmodel.BlogReadModels.UserProfileBlogs;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RdbBlogQueryRepositoryAdapter implements BlogQueryRepositoryPort {

    private final BlogQueryJpaRepository queryJpaRepository;
    private final BlogEntityMapper mapper;

    @Override
    public Optional<Blog> findById(String id) {
        Long longId = Long.valueOf(id);
        return queryJpaRepository.findById(longId)
                .map(mapper::toDomain);
    }

    @Override
    public Map<String, UserProfileBlogs> findAllByProfileIdIn(Set<String> userIds) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<String> findBlogIdsByUserId(String userId) {
        Long userIdLong = Long.valueOf(userId);
        return queryJpaRepository.findBlogIdByUserId(userIdLong).stream()
                .map(BlogIdProjection::id)
                .map(String::valueOf)
                .collect(Collectors.toSet());
    }
}
