package nettee.blolet.article.application.usecase;

import nettee.blolet.article.domain.type.DraftBlockStatus;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockSummary;

import java.util.List;

public interface DraftBlockReadByStatusUseCase {
    List<DraftBlockSummary> getDraftBlocksByStatus(String articleId, DraftBlockStatus status);
}
