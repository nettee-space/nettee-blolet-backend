package nettee.series.article.application.usecase;

import nettee.series.article.domain.SeriesArticle;

import java.util.List;

public interface SeriesArticleCreateUseCase {
    
    List<SeriesArticle> createSeriesArticle(String seriesId, List<SeriesArticle> articleList);
}
