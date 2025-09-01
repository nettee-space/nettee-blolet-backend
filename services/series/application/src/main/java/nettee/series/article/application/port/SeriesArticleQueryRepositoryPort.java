package nettee.series.article.application.port;

import nettee.blolet.article.readmodel.SeriesArticleQueryModels.SeriesArticleSummary;

import java.util.List;

public interface SeriesArticleQueryRepositoryPort {
    
    List<SeriesArticleSummary> findBySeriesId(String seriesId);
}
