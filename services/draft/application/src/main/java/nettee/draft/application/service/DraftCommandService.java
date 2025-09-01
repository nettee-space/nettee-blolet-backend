package nettee.draft.application.service;

import lombok.RequiredArgsConstructor;
import nettee.blolet.blog.export.client.api.BlogClient;
import nettee.draft.application.port.DraftCommandPort;
import nettee.draft.application.usecase.DraftImageCreateUseCase;
import nettee.draft.domain.Draft;
import nettee.draft.domain.DraftImage;
import nettee.draft.domain.type.DraftStatus;
import nettee.draft.application.usecase.DraftCreateUseCase;
import nettee.draft.application.usecase.DraftDeleteUseCase;
import nettee.draft.application.usecase.DraftUpdateUseCase;
import nettee.upload.port.ImageStorage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static nettee.draft.exception.DraftErrorCode.DRAFT_FORBIDDEN;
import static nettee.draft.exception.DraftErrorCode.DRAFT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class DraftCommandService implements DraftCreateUseCase, DraftUpdateUseCase, DraftDeleteUseCase, DraftImageCreateUseCase {
    private final DraftCommandPort draftCommandPort;
    private final BlogClient blogClient;
    private final ImageStorage imageStorage;

    @Override
    public Draft createDraft(String userId, Draft draft) {
        validateOwnership(userId, draft.getBlogId());
        return draftCommandPort.save(draft);
    }

    @Override
    public Draft updateDraft(String userId, Draft draft) {
        validateOwnership(userId, draft.getBlogId());
        return draftCommandPort.update(draft);
    }

    @Override
    public void deleteDraft(String userId, String draftId) {
        var blogId = draftCommandPort.findById(draftId)
                .orElseThrow(DRAFT_NOT_FOUND::exception)
                .blogId();
        validateOwnership(userId, blogId);

        draftCommandPort.updateStatus(draftId, DraftStatus.REMOVED);
    }

    @Override
    public DraftImage createDraftImage(String userId, String draftId, MultipartFile file, String targetName) {
        var blogId = draftCommandPort.findById(draftId)
                .orElseThrow(DRAFT_NOT_FOUND::exception)
                .blogId();
        validateOwnership(userId, blogId);

        var storedFileName = imageStorage.store(file, targetName);

        var imageUrl = imageStorage.getFileUrl(storedFileName, targetName);

        var draftImage = DraftImage.builder()
                .imageUrl(imageUrl)
                .build();

        return draftCommandPort.save(draftImage);
    }

    private void validateOwnership(String userId, String draftId) {
        var isOwner = blogClient.verifyOwnership(userId, draftId)
                .isOwner();

        if (!isOwner) {
            throw DRAFT_FORBIDDEN.exception();
        }
    }
}
