package nettee.draft.draftblock.application.service;

import lombok.RequiredArgsConstructor;
import nettee.draft.draftblock.application.port.DraftBlockCommandPort;
import nettee.draft.draftblock.application.usecase.DraftBlockCreateUseCase;
import nettee.draft.draftblock.application.usecase.DraftBlockDeleteUseCase;
import nettee.draft.draftblock.application.usecase.DraftBlockUpdateUseCase;
import nettee.draft.draftblock.domain.DraftBlock;
import nettee.draft.draftblock.domain.type.DraftBlockStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DraftBlockCommandService implements DraftBlockCreateUseCase, DraftBlockUpdateUseCase, DraftBlockDeleteUseCase {
    private final DraftBlockCommandPort draftblockCommandPort;

    @Override
    public DraftBlock createDraftBlock(DraftBlock draft) {
        return draftblockCommandPort.save(draft);
    }

    @Override
    public DraftBlock updateDraftBlock(DraftBlock draft) {
        return draftblockCommandPort.update(draft);
    }

    @Override
    public void deleteDraftBlock(String id) {
        draftblockCommandPort.updateStatus(id, DraftBlockStatus.DELETED);
    }
}
