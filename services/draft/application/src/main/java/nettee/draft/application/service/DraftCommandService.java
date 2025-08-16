package nettee.draft.application.service;

import lombok.RequiredArgsConstructor;
import nettee.draft.application.port.DraftCommandPort;
import nettee.draft.domain.Draft;
import nettee.draft.domain.type.DraftStatus;
import nettee.draft.application.usecase.DraftCreateUseCase;
import nettee.draft.application.usecase.DraftDeleteUseCase;
import nettee.draft.application.usecase.DraftUpdateUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DraftCommandService implements DraftCreateUseCase, DraftUpdateUseCase, DraftDeleteUseCase {
    private final DraftCommandPort draftCommandPort;

    @Override
    public Draft createDraft(String userId, Draft draft) {
        // TODO check if this user owns the blog
        return draftCommandPort.save(draft);
    }

    @Override
    public Draft updateDraft(String userId, Draft draft) {
        // TODO check if this user owns the blog
        return draftCommandPort.update(draft);
    }

    @Override
    public void deleteDraft(String userId, String draftId) {
        // TODO check if this user owns the blog
        draftCommandPort.updateStatus(draftId, DraftStatus.REMOVED);
    }
}
