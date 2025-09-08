package nettee.blolet.article.application.usecase;

import nettee.blolet.article.domain.Draft;
import nettee.blolet.article.domain.SeriesArticle;

public interface DraftPatchUseCase {

    Draft patchTitle(String userId, String draftId, String title);

    Draft patchPath(String userId, String draftId, String path);

    SeriesArticle patchSeriesArticle(String userId, String draftId, String seriesId, String articleId);
}
