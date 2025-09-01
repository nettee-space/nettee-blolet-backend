package nettee.blolet.article.application.usecase;

import nettee.blolet.article.domain.DraftBlock;

public interface DraftBlockUpdateUseCase {
    DraftBlock update(String userId, DraftBlock draftBlock);
}
