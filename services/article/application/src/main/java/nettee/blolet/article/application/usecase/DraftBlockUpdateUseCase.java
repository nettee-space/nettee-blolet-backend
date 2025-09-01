package nettee.blolet.article.application.usecase;

import nettee.draft.draftblock.domain.DraftBlock;

public interface DraftBlockUpdateUseCase {
    DraftBlock update(String userId, DraftBlock draftBlock);
}
