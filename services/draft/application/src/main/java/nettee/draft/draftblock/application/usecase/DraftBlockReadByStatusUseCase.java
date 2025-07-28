package nettee.draft.draftblock.application.usecase;

import nettee.draft.draftblock.domain.type.DraftBlockStatus;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockDetail;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockSummary;

import java.util.List;
import java.util.Optional;

public interface DraftBlockReadByStatusUseCase {
    List<DraftBlockSummary> getDraftBlocksByStatus(String articleId, DraftBlockStatus status);
}
