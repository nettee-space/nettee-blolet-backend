package nettee.blolet.article.rdb.persistence;

import nettee.blolet.article.rdb.entity.SeriesArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SeriesArticleJpaRepository extends JpaRepository<SeriesArticleEntity, Long> {
    
    SeriesArticleEntity findBySeriesIdAndDraftId(Long seriesId, Long draftId);
    
    @Modifying
    @Transactional
    @Query("delete from SeriesArticleEntity s where s.seriesId = :seriesId")
    void deleteAllBySeriesId(@Param("seriesId") Long seriesId);
}
