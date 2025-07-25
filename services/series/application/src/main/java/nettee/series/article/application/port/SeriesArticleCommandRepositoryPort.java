package nettee.series.article.application.port;

import nettee.series.article.domain.SeriesArticle;

import java.util.List;
import java.util.Optional;

public interface SeriesArticleCommandRepositoryPort {
    
    Optional<SeriesArticle> findBySeriesIdAndDraftId(String seriesId, String draftId);
    
    List<SeriesArticle> saveAll(List<SeriesArticle> article);
    
    SeriesArticle updateDraftToArticle(SeriesArticle article);
    
    void deleteAllBySeriesId(String seriesId);
}
