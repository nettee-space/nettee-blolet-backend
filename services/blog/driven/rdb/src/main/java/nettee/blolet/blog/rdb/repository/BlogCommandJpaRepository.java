package nettee.blolet.blog.rdb.repository;

import nettee.blolet.blog.rdb.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogCommandJpaRepository extends JpaRepository<BlogEntity, Long> {
    int countByUserId(Long userId);
}
