package nettee.series.article.application.usecase;

import nettee.blolet.article.domain.SeriesArticle;

import java.util.List;

public interface SeriesArticleCreateUseCase {
    
    List<SeriesArticle> createSeriesArticleList(String seriesId, List<SeriesArticle> articleList);
}
