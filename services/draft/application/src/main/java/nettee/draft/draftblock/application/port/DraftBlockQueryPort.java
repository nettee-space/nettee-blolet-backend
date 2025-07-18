package nettee.draft.draftblock.application.port;

import nettee.draft.draftblock.domain.type.DraftBlockStatus;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockDetail;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.Set;

public interface DraftBlockQueryPort {
    Optional<DraftBlockDetail> findById(String id);
    Page<DraftBlockSummary> findAll(Pageable pageable);

    Page<DraftBlockSummary> findByStatuses(Set<DraftBlockStatus> statuses, Pageable pageable);

}
