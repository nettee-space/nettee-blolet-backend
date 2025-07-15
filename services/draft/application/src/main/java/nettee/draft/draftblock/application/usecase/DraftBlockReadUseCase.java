package nettee.draft.draftblock.application.usecase;

import nettee.draft.draftblock.readmodel.DraftBlockQueryModels.DraftBlockDetail;
import nettee.draft.draftblock.readmodel.DraftBlockQueryModels.DraftBlockSummary;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface DraftBlockReadUseCase {
    Optional<DraftBlockDetail> getDraftBlock(Long id);
    Page<DraftBlockSummary> getAllDraftBlock(int size);
}
