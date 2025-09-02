package nettee.blolet.article.application.usecase;

import nettee.blolet.article.domain.DraftImage;
import org.springframework.web.multipart.MultipartFile;

public interface DraftImageCreateUseCase {
    DraftImage createDraftImage(String userId, String draftId, MultipartFile file, String targetName);
}
