package nettee.series.article.application.usecase;

import nettee.series.article.domain.SeriesArticle;

import java.util.List;

public interface SeriesArticleReadUseCase {
    
    List<SeriesArticle> getSeriesArticleList(String seriesId);
}
