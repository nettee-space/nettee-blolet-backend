package nettee.series.article.application.port;

import nettee.series.article.domain.SeriesArticle;

import java.util.List;

public interface SeriesArticleQueryRepositoryPort {
    
    List<SeriesArticle>findBySeriesId (String seriesId);
}
