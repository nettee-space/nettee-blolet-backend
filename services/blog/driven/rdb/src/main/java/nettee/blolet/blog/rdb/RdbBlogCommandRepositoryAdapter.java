package nettee.blolet.blog.rdb;

import lombok.RequiredArgsConstructor;
import nettee.blolet.blog.application.port.BlogCommandRepositoryPort;
import nettee.blolet.blog.domain.Blog;
import nettee.blolet.blog.rdb.mapper.BlogEntityMapper;
import nettee.blolet.blog.rdb.repository.BlogCommandJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class RdbBlogCommandRepositoryAdapter implements BlogCommandRepositoryPort {

    private final BlogCommandJpaRepository jpaRepository;
    private final BlogEntityMapper mapper;

    @Override
    @Transactional
    public void transaction(Runnable runnable) {
        runnable.run();
    }

    @Override
    public Blog save(Blog blog) {
        var entity = mapper.toEntity(blog);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Blog update(Blog blog) {
        Long id = Long.parseLong(blog.getId());
        var entity = jpaRepository.findById(id)
                .orElseThrow(BLOG_NOT_FOUND::exception);

        entity.name = blog.getName();
        entity.urlIdentifier = blog.getUrlIdentifier();

        return mapper.toDomain(entity);
    }

    @Override
    public Blog updateUserProfile(String id, String username, String nickname) {
        Long longId = Long.parseLong(id);
        var entity = jpaRepository.findById(longId)
                .orElseThrow(BLOG_NOT_FOUND::exception);

        entity.username = username;
        entity.nickname = nickname;

        return mapper.toDomain(entity);
    }

    @Override
    public void deleteById(String id) {
        jpaRepository.deleteById(Long.parseLong(id));
    }

    @Override
    public Optional<Blog> findById(String id) {
        return jpaRepository.findById(Long.parseLong(id))
                .map(mapper::toDomain);
    }

    @Override
    public int countByUserId(String userId) {
        return jpaRepository.countByUserId(Long.parseLong(userId));
    }

    @Override
    public boolean existsById(String id) {
        return jpaRepository.existsById(Long.parseLong(id));
    }
}
