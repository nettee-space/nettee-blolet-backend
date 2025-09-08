package nettee.blolet.article.application.port;

import nettee.blolet.article.domain.Draft;
import nettee.blolet.article.domain.DraftImage;
import nettee.blolet.article.domain.SeriesArticle;
import nettee.blolet.article.domain.sub.DraftStatus;
import nettee.blolet.article.readmodel.DraftReadModels.DraftDetail;

import java.util.Optional;

public interface DraftCommandPort {

    Optional<DraftDetail> findDraftById(String id);

    Optional<SeriesArticle> findSeriesArticleById(String id);

    Draft save(Draft draft);

    Draft update(Draft draft);

    void updateStatus(String id, DraftStatus draftStatus);

    DraftImage save(DraftImage draftImage);

    Draft updateTitle(String draftId, String title);

    Draft updatePath(String draftId, String path);

    SeriesArticle updateSeriesArticle(String draftId, String seriesId, String articleId);
}
