package nettee.draft.draftblock.application.usecase;

import nettee.draft.draftblock.domain.DraftBlock;

public interface DraftBlockCreateUseCase {
        DraftBlock createDraftBlock(String userId, DraftBlock draftBlock);
}
