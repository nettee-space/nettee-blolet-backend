package nettee.blolet.article.application.service;

import lombok.RequiredArgsConstructor;
import nettee.blolet.article.application.port.DraftBlockCommandPort;
import nettee.blolet.article.application.usecase.DraftBlockCreateUseCase;
import nettee.blolet.article.application.usecase.DraftBlockDeleteUseCase;
import nettee.blolet.article.application.usecase.DraftBlockUpdateUseCase;
import nettee.blolet.article.domain.DraftBlock;
import nettee.blolet.article.domain.sub.DraftBlockStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DraftBlockCommandService implements DraftBlockCreateUseCase, DraftBlockUpdateUseCase, DraftBlockDeleteUseCase {
    private final DraftBlockCommandPort draftblockCommandPort;

    @Override
    public DraftBlock create(String userId, DraftBlock draft) {
        // TODO validate if this user owns the blog
        return draftblockCommandPort.save(draft);
    }

    @Override
    public DraftBlock update(String userId, DraftBlock draft) {
        // TODO validate if this user owns the blog
        return draftblockCommandPort.update(draft);
    }

    @Override
    public void delete(String userId, String draftBlockId) {
        // TODO validate if this user owns the blog
        draftblockCommandPort.updateStatus(draftBlockId, DraftBlockStatus.REMOVED);
    }
}
