package nettee.draft.application.usecase;

import nettee.draft.readmodel.DraftReadModels.DraftDetail;
import nettee.draft.readmodel.DraftReadModels.DraftTitle;

import java.util.List;
import java.util.Optional;

public interface DraftReadUseCase {
    Optional<DraftDetail> getDraft(String id);
    List<DraftTitle> getDraftTitlesByIds(List<String> ids);
}
