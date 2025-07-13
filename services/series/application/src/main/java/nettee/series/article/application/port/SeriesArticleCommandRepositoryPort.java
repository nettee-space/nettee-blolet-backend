package nettee.series.article.application.port;

import nettee.series.article.domain.SeriesArticle;

public interface SeriesArticleCommandRepositoryPort {
    
    SeriesArticle save (SeriesArticle article);
    
    SeriesArticle updateDraftToArticle (SeriesArticle article);
    
    void delete (String seriesId);
}
