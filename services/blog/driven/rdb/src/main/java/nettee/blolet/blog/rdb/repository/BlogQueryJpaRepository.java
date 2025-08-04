package nettee.blolet.blog.rdb.repository;

import nettee.blolet.blog.rdb.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogQueryJpaRepository extends JpaRepository<BlogEntity, Long> {
}
