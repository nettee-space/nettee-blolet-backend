package nettee.draft.application.port;

import nettee.draft.readmodel.DraftReadModels.DraftDetail;
import nettee.draft.readmodel.DraftReadModels.DraftSummary;
import nettee.draft.domain.type.DraftStatus;
import nettee.draft.readmodel.DraftReadModels.DraftTitle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface DraftQueryPort {
    Optional<DraftDetail> findById(String id);
    Map<String, DraftTitle> findTitlesById(Set<String> id);
    List<DraftSummary> findByStatuses(String blogId, Set<DraftStatus> statuses, String sortBy, boolean ascending);

}
