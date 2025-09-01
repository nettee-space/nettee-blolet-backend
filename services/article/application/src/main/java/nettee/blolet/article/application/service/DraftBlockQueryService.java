package nettee.blolet.article.application.service;

import lombok.RequiredArgsConstructor;
import nettee.blolet.article.application.port.DraftBlockQueryPort;
import nettee.blolet.article.application.usecase.DraftBlockReadByStatusUseCase;
import nettee.blolet.article.application.usecase.DraftBlockReadUseCase;
import nettee.blolet.article.domain.type.DraftBlockStatus;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockDetail;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockSummary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DraftBlockQueryService implements DraftBlockReadUseCase, DraftBlockReadByStatusUseCase {
    private final DraftBlockQueryPort draftBlockQueryPort;

    @Override
    public Optional<DraftBlockDetail> getDraftBlock(String id) {
        return draftBlockQueryPort.findById(id);
    }

    @Override
    public List<DraftBlockSummary> getDraftBlocksByStatus(String articleId, DraftBlockStatus status) {
        return draftBlockQueryPort.findByStatus(articleId, status);
    }
}
