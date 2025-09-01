package nettee.blolet.article.application.usecase;

import nettee.blolet.article.domain.Draft;

public interface DraftUpdateUseCase {
    Draft updateDraft(String userId, Draft draft);
}
