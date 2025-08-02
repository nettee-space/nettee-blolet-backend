package nettee.draft.application.usecase;

import nettee.draft.readmodel.DraftReadModels.DraftDetail;
import nettee.draft.readmodel.DraftReadModels.DraftTitle;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface DraftReadUseCase {
    Optional<DraftDetail> getDraft(String id);
    Map<String, DraftTitle> getDraftTitlesByIds(Set<String> ids);
}
