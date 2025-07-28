package nettee.draft.application.usecase;

import nettee.draft.readmodel.DraftReadModels.DraftSummary;
import nettee.draft.domain.type.DraftStatus;

import java.util.List;
import java.util.Set;

public interface DraftReadByStatusesUseCase {
    List<DraftSummary> getDraftsByStatuses(String blogId, Set<DraftStatus> statuses, String sortBy, boolean ascending);
}
