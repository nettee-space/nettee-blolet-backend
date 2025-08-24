package nettee.draft.application.port;

import nettee.draft.domain.DraftImage;
import nettee.draft.readmodel.DraftReadModels.DraftDetail;
import nettee.draft.domain.Draft;
import nettee.draft.domain.type.DraftStatus;
import java.util.Optional;

public interface DraftCommandPort {

    Optional<DraftDetail> findById(String id);

    Draft save(Draft draft);

    Draft update(Draft draft);

    void updateStatus(String id, DraftStatus draftStatus);

    DraftImage save(DraftImage draftImage);
}
