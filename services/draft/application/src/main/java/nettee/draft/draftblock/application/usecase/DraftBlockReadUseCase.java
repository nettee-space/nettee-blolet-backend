package nettee.draft.draftblock.application.usecase;

import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockDetail;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockSummary;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface DraftBlockReadUseCase {
    Optional<DraftBlockDetail> getDraftBlock(String id);
    List<DraftBlockSummary> getDraftBlocks(String articleId);
}
