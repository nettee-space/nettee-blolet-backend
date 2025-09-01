package nettee.blolet.article.application.usecase;

import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockDetail;
import java.util.Optional;

public interface DraftBlockReadUseCase {
    Optional<DraftBlockDetail> getDraftBlock(String id);
}
