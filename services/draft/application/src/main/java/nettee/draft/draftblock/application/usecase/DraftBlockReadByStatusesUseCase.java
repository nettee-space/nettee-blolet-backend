package nettee.draft.draftblock.application.usecase;

import nettee.draft.draftblock.domain.type.DraftBlockStatus;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockSummary;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface DraftBlockReadByStatusesUseCase {
    Page<DraftBlockSummary> findByStatuses(Set<DraftBlockStatus> statuses, int size);
}
