package nettee.blolet.article.application.usecase;

import nettee.draft.draftblock.domain.DraftBlock;

public interface DraftBlockCreateUseCase {
        DraftBlock create(String userId, DraftBlock draftBlock);
}
