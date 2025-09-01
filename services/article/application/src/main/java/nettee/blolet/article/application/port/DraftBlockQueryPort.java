package nettee.blolet.article.application.port;

import nettee.blolet.article.domain.type.DraftBlockStatus;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockDetail;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockSummary;

import java.util.List;
import java.util.Optional;

public interface DraftBlockQueryPort {
    Optional<DraftBlockDetail> findById(String id);
    List<DraftBlockSummary> findByStatus(String articleId, DraftBlockStatus status);
}
