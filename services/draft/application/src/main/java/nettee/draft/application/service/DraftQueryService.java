package nettee.draft.application.service;

import lombok.RequiredArgsConstructor;
import nettee.draft.readmodel.DraftReadModels.DraftDetail;
import nettee.draft.readmodel.DraftReadModels.DraftSummary;
import nettee.draft.application.port.DraftQueryPort;
import nettee.draft.domain.type.DraftStatus;
import nettee.draft.application.usecase.DraftReadByStatusesUseCase;
import nettee.draft.application.usecase.DraftReadUseCase;
import nettee.draft.readmodel.DraftReadModels.DraftTitle;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DraftQueryService implements DraftReadUseCase, DraftReadByStatusesUseCase {
    private final DraftQueryPort draftQueryPort;

    @Override
    public Optional<DraftDetail> getDraft(String id) {
        return draftQueryPort.findById(id);
    }

    @Override
    public Map<String, DraftTitle> getDraftTitlesByIds(Set<String> ids) {
        return draftQueryPort.findTitlesById(ids);
    }

    @Override
    public List<DraftSummary> getDraftsByStatuses(String blogId, Set<DraftStatus> statuses, String sortBy, boolean ascending) {
        return draftQueryPort.findByStatuses(blogId, statuses, sortBy, ascending);
    }

}
