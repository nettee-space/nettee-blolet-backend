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

import static nettee.blolet.article.exception.DraftErrorCode.SERIES_ALREADY_REGISTERED;
import static nettee.blolet.article.exception.DraftErrorCode.BLOG_MISMATCH;
import static nettee.blolet.article.exception.DraftErrorCode.DRAFT_FORBIDDEN;
import static nettee.blolet.article.exception.DraftErrorCode.DRAFT_NOT_FOUND;
import static nettee.blolet.article.exception.DraftErrorCode.SERIES_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class DraftCommandService implements
        DraftCreateUseCase,
        DraftUpdateUseCase,
        DraftDeleteUseCase,
        DraftImageCreateUseCase,
        DraftPatchUseCase {

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
    public SeriesArticle registerSeriesArticle(String userId, String draftId, String seriesId, String articleId) {
        // 드래프트, 시리즈 조회
        var draft = draftCommandPort.findDraftById(draftId)
            .orElseThrow(DRAFT_NOT_FOUND::exception);

        var series = draftCommandPort.findSeriesById(seriesId)
            .orElseThrow(SERIES_NOT_FOUND::exception);

        // 권한 검증
        validateOwnership(userId, draft.blogId());
        validateOwnership(userId, series.blogId());

        // 블로그 동일 여부 확인
        if (!draft.blogId().equals(series.blogId())) {
            throw BLOG_MISMATCH.exception();
        }

        // 시리즈 존재 여부 확인
        if (draftCommandPort.existsSeriesArticle(draftId, seriesId)) {
            throw SERIES_ALREADY_REGISTERED.exception();
        }

        // 시리즈 등록
        var seriesArticle = draftCommandPort.createSeriesArticle(draftId, seriesId, articleId);

        // 드래프트에 시리즈 정보 업데이트
        draftCommandPort.updateDraftSeriesInfo(draftId, seriesId);

        return seriesArticle;
    }

    private void validateOwnership(String userId, String draftId) {
        var isOwner = blogClient.verifyOwnership(userId, draftId)
                .isOwner();

        if (!isOwner) {
            throw DRAFT_FORBIDDEN.exception();
        }
    }
}
