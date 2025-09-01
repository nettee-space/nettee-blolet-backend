package nettee.blolet.article.application.usecase;

import nettee.blolet.article.domain.DraftBlock;

public interface DraftBlockCreateUseCase {
        DraftBlock create(String userId, DraftBlock draftBlock);
}
