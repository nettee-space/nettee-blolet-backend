package nettee.article.service;

import lombok.RequiredArgsConstructor;
import nettee.article.domain.ArticleLike;
import nettee.article.port.ArticleLikesCommandRepositoryPort;
import nettee.article.usecase.ArticleLikeCreateUseCase;
import nettee.article.usecase.ArticleLikeDeleteUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleLikesCommandService implements ArticleLikeCreateUseCase, ArticleLikeDeleteUseCase {

    private final ArticleLikesCommandRepositoryPort articleLikesCommandRepository;

    @Override
    public ArticleLike createArticleLike(String profileId, String articleId) {
        return articleLikesCommandRepository.save(profileId, articleId);
    }

    @Override
    public void deleteArticleLike(String profileId, String articleId) {
        articleLikesCommandRepository.delete(profileId, articleId);
    }
}
