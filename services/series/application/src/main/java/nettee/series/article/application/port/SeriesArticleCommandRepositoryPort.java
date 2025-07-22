package nettee.series.article.application.port;

import nettee.series.article.domain.SeriesArticle;

import java.util.Optional;

public interface SeriesArticleCommandRepositoryPort {

    Optional<SeriesArticle> findByIdAndDraftId(String seriesId, String draftId);

    SeriesArticle save (SeriesArticle article);
    
    SeriesArticle updateDraftToArticle (SeriesArticle article);
    
    void delete (String seriesId);
}
