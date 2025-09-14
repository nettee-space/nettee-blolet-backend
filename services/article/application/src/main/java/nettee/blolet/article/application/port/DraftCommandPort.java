package nettee.blolet.article.application.port;

import nettee.blolet.article.domain.Draft;
import nettee.blolet.article.domain.DraftImage;
import nettee.blolet.article.domain.SeriesArticle;
import nettee.blolet.article.domain.sub.DraftStatus;
import nettee.blolet.article.readmodel.DraftReadModels.DraftDetail;
import nettee.blolet.article.readmodel.SeriesQueryModels.SeriesDetail;

import java.util.Optional;

public interface DraftCommandPort {

    Optional<DraftDetail> findDraftById(String id);
    Optional<SeriesDetail> findSeriesById(String seriesId);

    boolean existsSeriesArticle(String draftId, String seriesId);

    Draft save(Draft draft);
    DraftImage save(DraftImage draftImage);
    SeriesArticle createSeriesArticle(String draftId, String seriesId, String articleId);

    Draft update(Draft draft);
    Draft updateTitle(String draftId, String title);
    Draft updatePath(String draftId, String path);
    SeriesArticle updateSeriesArticle(String draftId, String seriesId, String articleId);
    void updateDraftSeriesInfo(String draftId, String seriesId);
    void updateStatus(String id, DraftStatus draftStatus);
}
