package nettee.article.service;

import lombok.RequiredArgsConstructor;
import nettee.article.domain.ArticleLikes;
import nettee.article.port.ArticleLikesCommandRepositoryPort;
import nettee.article.usecase.ArticleLikesUpdateUseCase;
import nettee.article.usecase.ArticleLikesDeleteUseCase;
import org.springframework.stereotype.Service;

import static nettee.article.exception.ArticleErrorCode.ARTICLE_LIKE_SUSPENDED;

@Service
@RequiredArgsConstructor
public class ArticleLikesCommandService implements ArticleLikesUpdateUseCase, ArticleLikesDeleteUseCase {

    private final ArticleLikesCommandRepositoryPort commandRepository;

    @Override
    public ArticleLikes updateArticleLikes(ArticleLikes articleLikes) {
        ArticleLikes existingDomain = commandRepository.findByProfileIdAndArticleId(
                articleLikes.getProfileId(),
                articleLikes.getArticleId()
        ).orElse(articleLikes);

        switch (existingDomain.getStatus()) {
            case null -> articleLikes.activate();
            case ACTIVE -> {/* path through */}
            case REMOVED -> articleLikes.activate();
            case SUSPENDED -> throw ARTICLE_LIKE_SUSPENDED.exception();
            default -> throw new Error("서버의 데이터 불일치로 요청을 처리할 수 없습니다.");
        }

        return commandRepository.save(articleLikes);
    }

    @Override
    public void deleteArticleLikes(String profileId, String articleId) {
        commandRepository.deleteByProfileIdAndArticleId(profileId, articleId);
    }
}
