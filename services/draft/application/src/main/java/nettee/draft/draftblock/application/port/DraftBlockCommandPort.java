package nettee.draft.draftblock.application.port;

import nettee.draft.draftblock.domain.DraftBlock;
import nettee.draft.draftblock.domain.type.DraftBlockStatus;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockDetail;

import java.util.Optional;

public interface DraftBlockCommandPort {
    Optional<DraftBlockDetail> findById(String id);
    DraftBlock save(DraftBlock draft);
    DraftBlock update(DraftBlock draft);
    void updateStatus(String id, DraftBlockStatus draftStatus);
}
