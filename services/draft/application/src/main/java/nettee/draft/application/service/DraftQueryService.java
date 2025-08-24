package nettee.draft.application.service;

import lombok.RequiredArgsConstructor;
import nettee.blolet.draft.api.validation.context.DraftContextValidationSupplier;
import nettee.common.validation.model.ValidationResponseModel;
import nettee.draft.application.usecase.DraftValidationResponseUseCase;
import nettee.draft.readmodel.DraftReadModels.DraftDetail;
import nettee.draft.readmodel.DraftReadModels.DraftSummary;
import nettee.draft.application.port.DraftQueryPort;
import nettee.draft.domain.type.DraftStatus;
import nettee.draft.application.usecase.DraftReadByStatusesUseCase;
import nettee.draft.application.usecase.DraftReadUseCase;
import nettee.draft.readmodel.DraftReadModels.DraftTitle;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DraftQueryService implements DraftReadUseCase, DraftReadByStatusesUseCase, DraftValidationResponseUseCase {

    private final DraftQueryPort draftQueryPort;
    private final DraftContextValidationSupplier draftContextValidationSupplier;

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

    @Override
    public ValidationResponseModel responseValidation(String context) {
        return draftContextValidationSupplier.get(context);
    }
}
