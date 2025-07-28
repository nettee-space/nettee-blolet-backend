package nettee.draft.draftblock.application.service;

import lombok.RequiredArgsConstructor;
import nettee.draft.draftblock.application.port.DraftBlockQueryPort;
import nettee.draft.draftblock.application.usecase.DraftBlockReadUseCase;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockDetail;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockSummary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DraftBlockQueryService implements DraftBlockReadUseCase {
    private final DraftBlockQueryPort draftBlockQueryPort;

    @Override
    public Optional<DraftBlockDetail> getDraftBlock(String id) {
        return draftBlockQueryPort.findById(id);
    }

    @Override
    public List<DraftBlockSummary> getDraftBlocks(String articleId) {
        return draftBlockQueryPort.findAll(articleId);
    }
}
