package nettee.series.article.application.usecase;

import nettee.series.article.readmodel.SeriesArticleQueryModels;

import java.util.List;

public interface SeriesArticleReadUseCase {
    
    List<SeriesArticleQueryModels.SeriesArticleSummary> getSeriesArticleList(String seriesId);
}
