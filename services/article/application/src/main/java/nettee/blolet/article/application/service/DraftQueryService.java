package nettee.blolet.article.application.service;

import lombok.RequiredArgsConstructor;
import nettee.blolet.article.api.validation.context.DraftContextValidationSupplier;
import nettee.common.validation.model.ValidationResponseModel;
import nettee.blolet.article.application.usecase.DraftValidationResponseUseCase;
import nettee.draft.readmodel.DraftReadModels.DraftDetail;
import nettee.draft.readmodel.DraftReadModels.DraftSummary;
import nettee.blolet.article.application.port.DraftQueryPort;
import nettee.blolet.article.domain.type.DraftStatus;
import nettee.blolet.article.application.usecase.DraftReadByStatusesUseCase;
import nettee.blolet.article.application.usecase.DraftReadUseCase;
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
