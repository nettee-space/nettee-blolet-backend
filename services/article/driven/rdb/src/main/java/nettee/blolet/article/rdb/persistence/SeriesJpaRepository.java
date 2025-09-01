package nettee.blolet.article.rdb.persistence;

import nettee.blolet.article.rdb.entity.SeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesJpaRepository extends JpaRepository<SeriesEntity, Long> {
    
    boolean existsByBlogIdAndTitle(Long blogId, String title);
}
