package nettee.series.article.application.usecase;

import nettee.series.article.readmodel.SeriesArticleQueryModels.SeriesArticleSummary;

import java.util.List;

public interface SeriesArticleReadUseCase {
    
    List<SeriesArticleSummary> findBySeriesId(String seriesId);
}
