package nettee.blolet.article.application.usecase;

import nettee.blolet.article.domain.Draft;

public interface DraftCreateUseCase {
    Draft createDraft(String userId, Draft draft);
}
