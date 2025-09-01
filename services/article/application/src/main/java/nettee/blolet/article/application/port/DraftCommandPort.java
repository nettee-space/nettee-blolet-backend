package nettee.blolet.article.application.port;

import nettee.blolet.article.domain.Draft;
import nettee.blolet.article.domain.DraftImage;
import nettee.blolet.article.domain.sub.DraftStatus;
import nettee.blolet.article.readmodel.DraftReadModels.DraftDetail;

import java.util.Optional;

public interface DraftCommandPort {

    Optional<DraftDetail> findById(String id);

    Draft save(Draft draft);

    Draft update(Draft draft);

    void updateStatus(String id, DraftStatus draftStatus);

    DraftImage save(DraftImage draftImage);
}
