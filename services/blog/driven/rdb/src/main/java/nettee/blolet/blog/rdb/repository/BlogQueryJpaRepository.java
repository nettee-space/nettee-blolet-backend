package nettee.blolet.blog.rdb.repository;

import nettee.blolet.blog.rdb.entity.BlogEntity;
import nettee.blolet.blog.rdb.repository.projection.BlogQueryProjection.BlogIdProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface BlogQueryJpaRepository extends JpaRepository<BlogEntity, Long> {
    Set<BlogIdProjection> findBlogIdByUserId(Long userId);
}
