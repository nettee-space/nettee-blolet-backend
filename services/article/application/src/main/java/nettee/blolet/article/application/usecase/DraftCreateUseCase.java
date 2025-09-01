package nettee.blolet.article.application.usecase;

import nettee.draft.domain.Draft;

public interface DraftCreateUseCase {
    Draft createDraft(String userId, Draft draft);
}
