package nettee.series.article.application.usecase;

import nettee.series.article.domain.SeriesArticle;
import nettee.series.article.readmodel.SeriesArticleQueryModels.SeriesArticleSummary;

import java.util.List;

public interface SeriesArticleCreateUseCase {
    
    List<SeriesArticleSummary> createSeriesArticle(SeriesArticle article);
}
