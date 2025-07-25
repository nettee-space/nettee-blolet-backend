package nettee.series.article.driven.rdb.persistence;

import nettee.series.article.driven.rdb.entity.SeriesArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SeriesArticleJpaRepository extends JpaRepository<SeriesArticleEntity, Long> {
    
    SeriesArticleEntity findBySeriesIdAndDraftId(Long seriesId, Long draftId);
    
    @Modifying
    @Transactional
    @Query("delete from series_article s where s.seriesId = :seriesId")
    void deleteBySeriesId(@Param("seriesId") Long seriesId);
}
