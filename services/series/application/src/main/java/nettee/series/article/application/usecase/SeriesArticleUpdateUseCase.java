package nettee.series.article.application.usecase;

import nettee.blolet.article.domain.SeriesArticle;

public interface SeriesArticleUpdateUseCase {
    
    SeriesArticle updateDraftToArticle(String seriesId, String draftId, String articleId);
}
