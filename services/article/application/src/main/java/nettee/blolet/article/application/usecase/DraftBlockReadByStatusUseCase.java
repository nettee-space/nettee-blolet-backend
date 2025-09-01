package nettee.blolet.article.application.usecase;

import nettee.blolet.article.domain.sub.DraftBlockStatus;
import nettee.blolet.article.readmodel.DraftBlockReadModels.DraftBlockSummary;

import java.util.List;

public interface DraftBlockReadByStatusUseCase {
    List<DraftBlockSummary> getDraftBlocksByStatus(String articleId, DraftBlockStatus status);
}
