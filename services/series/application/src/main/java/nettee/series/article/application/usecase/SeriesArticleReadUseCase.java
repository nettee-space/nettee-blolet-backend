package nettee.series.article.application.usecase;

import nettee.blolet.article.readmodel.SeriesArticleQueryModels.SeriesArticleSummary;

import java.util.List;

public interface SeriesArticleReadUseCase {
    
    List<SeriesArticleSummary> getSeriesArticleList(String seriesId);
}
