package nettee.draft.application.usecase;

import nettee.draft.domain.DraftImage;
import org.springframework.web.multipart.MultipartFile;

public interface DraftImageCreateUseCase {
    DraftImage createDraftImage(MultipartFile file, String targetName);
}
