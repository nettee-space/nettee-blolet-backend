package nettee.draft.draftblock.application.usecase;

import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockDetail;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockSummary;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface DraftBlockReadUseCase {
    Optional<DraftBlockDetail> getDraftBlock(String id);
    Page<DraftBlockSummary> getAllDraftBlock(int size);
}
