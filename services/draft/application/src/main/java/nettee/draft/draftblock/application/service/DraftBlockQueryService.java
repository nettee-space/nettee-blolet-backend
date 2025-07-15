package nettee.draft.draftblock.application.service;

import lombok.RequiredArgsConstructor;
import nettee.draft.draftblock.application.port.DraftBlockQueryPort;
import nettee.draft.draftblock.application.usecase.DraftBlockReadByStatusesUseCase;
import nettee.draft.draftblock.application.usecase.DraftBlockReadUseCase;
import nettee.draft.draftblock.domain.type.DraftBlockStatus;
import nettee.draft.draftblock.readmodel.DraftBlockQueryModels.DraftBlockDetail;
import nettee.draft.draftblock.readmodel.DraftBlockQueryModels.DraftBlockSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DraftBlockQueryService implements DraftBlockReadUseCase, DraftBlockReadByStatusesUseCase {
    private final DraftBlockQueryPort draftBlockQueryPort;

    @Override
    public Optional<DraftBlockDetail> getDraftBlock(Long id) {
        return draftBlockQueryPort.findById(id);
    }

    @Override
    public Page<DraftBlockSummary> getAllDraftBlock(int size) {
        Pageable pageable = PageRequest.of(0, size, Sort.by(Direction.DESC, "createAt"));
        return draftBlockQueryPort.findAll(pageable);
    }

    @Override
    public Page<DraftBlockSummary> findByStatuses(Set<DraftBlockStatus> statuses, int size) {
        Pageable pageable = PageRequest.of(0, size, Sort.by(Direction.DESC, "createAt"));
        return draftBlockQueryPort.findByStatuses(statuses, pageable);
    }

}
