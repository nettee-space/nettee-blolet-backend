package nettee.series.driven.rdb.persistence;

import nettee.series.driven.rdb.entity.SeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesJpaRepository extends JpaRepository<SeriesEntity, Long> {
    
    boolean existsByBlogIdAndTitle(Long blogId, String title);
    
    long countByBlogId(Long blogId);
}
