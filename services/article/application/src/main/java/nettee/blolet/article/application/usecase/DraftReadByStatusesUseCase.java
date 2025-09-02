package nettee.blolet.article.application.usecase;

import nettee.blolet.article.domain.sub.DraftStatus;
import nettee.blolet.article.readmodel.DraftReadModels.DraftSummary;

import java.util.List;
import java.util.Set;

public interface DraftReadByStatusesUseCase {
    List<DraftSummary> getDraftsByStatuses(String blogId, Set<DraftStatus> statuses, String sortBy, boolean ascending);
}
