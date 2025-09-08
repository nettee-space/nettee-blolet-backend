package nettee.blolet.article.application.service;

import lombok.RequiredArgsConstructor;
import nettee.blolet.article.application.port.DraftCommandPort;
import nettee.blolet.article.application.usecase.DraftCreateUseCase;
import nettee.blolet.article.application.usecase.DraftDeleteUseCase;
import nettee.blolet.article.application.usecase.DraftImageCreateUseCase;
import nettee.blolet.article.application.usecase.DraftPatchUseCase;
import nettee.blolet.article.application.usecase.DraftUpdateUseCase;
import nettee.blolet.article.domain.Draft;
import nettee.blolet.article.domain.DraftImage;
import nettee.blolet.article.domain.SeriesArticle;
import nettee.blolet.article.domain.sub.DraftStatus;
import nettee.blolet.blog.export.client.api.BlogClient;
import nettee.upload.port.ImageStorage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static nettee.blolet.article.exception.DraftErrorCode.DRAFT_FORBIDDEN;
import static nettee.blolet.article.exception.DraftErrorCode.DRAFT_NOT_FOUND;
import static nettee.blolet.article.exception.DraftErrorCode.SERIES_ARTICLE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class DraftCommandService implements
    DraftCreateUseCase,
    DraftUpdateUseCase,
    DraftDeleteUseCase,
    DraftImageCreateUseCase,
    DraftPatchUseCase
{

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
        var blogId = draftCommandPort.findDraftById(draftId)
                .orElseThrow(DRAFT_NOT_FOUND::exception)
                .blogId();
        validateOwnership(userId, blogId);

        draftCommandPort.updateStatus(draftId, DraftStatus.REMOVED);
    }

    @Override
    public DraftImage createDraftImage(String userId, String draftId, MultipartFile file, String targetName) {
        var blogId = draftCommandPort.findDraftById(draftId)
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

    @Override
    public Draft patchTitle(String userId, String draftId, String title) {
        var draft = draftCommandPort.findDraftById(draftId)
            .orElseThrow(DRAFT_NOT_FOUND::exception);

        validateOwnership(userId, draft.blogId());

        return draftCommandPort.updateTitle(draft.id(), title);
    }

    @Override
    public Draft patchPath(String userId, String draftId, String path) {
        var draft = draftCommandPort.findDraftById(draftId)
            .orElseThrow(DRAFT_NOT_FOUND::exception);

        validateOwnership(userId, draft.blogId());

        return draftCommandPort.updatePath(draft.id(), path);
    }

    @Override
    public SeriesArticle patchSeriesArticle(String userId, String draftId, String seriesId, String articleId) {
        var seriesArticle = draftCommandPort.findSeriesArticleById(draftId)
            .orElseThrow(SERIES_ARTICLE_NOT_FOUND::exception);

        validateOwnership(userId, draftId);

        return draftCommandPort.updateSeriesArticle(seriesArticle.getDraftId(), seriesId, articleId);
    }
}
