package nettee.blolet.article.application.port;

import nettee.blolet.article.domain.DraftBlock;
import nettee.blolet.article.domain.sub.DraftBlockStatus;
import nettee.blolet.article.readmodel.DraftBlockReadModels.DraftBlockDetail;

import java.util.Optional;

public interface DraftBlockCommandPort {
    Optional<DraftBlockDetail> findById(String id);
    DraftBlock save(DraftBlock draft);
    DraftBlock update(DraftBlock draft);
    void updateStatus(String id, DraftBlockStatus draftStatus);
}
