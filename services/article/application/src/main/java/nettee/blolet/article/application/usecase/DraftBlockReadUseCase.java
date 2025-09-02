package nettee.blolet.article.application.usecase;

import nettee.blolet.article.readmodel.DraftBlockReadModels.DraftBlockDetail;

import java.util.Optional;

public interface DraftBlockReadUseCase {
    Optional<DraftBlockDetail> getDraftBlock(String id);
}
