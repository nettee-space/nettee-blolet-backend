package nettee.article.service;

import lombok.RequiredArgsConstructor;
import nettee.article.domain.ArticleLikes;
import nettee.article.port.ArticleLikesCommandRepositoryPort;
import nettee.article.usecase.ArticleLikesUpdateUseCase;
import nettee.article.usecase.ArticleLikesDeleteUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleLikesCommandService implements ArticleLikesUpdateUseCase, ArticleLikesDeleteUseCase {

    private final ArticleLikesCommandRepositoryPort articleLikesCommandRepository;

    @Override
    public ArticleLikes updateArticleLikes(ArticleLikes articleLikes) {
        return articleLikesCommandRepository.save(articleLikes);
    }

    @Override
    public void deleteArticleLikes(String profileId, String articleId) {
        articleLikesCommandRepository.deleteByProfileIdAndArticleId(profileId, articleId);
    }
}
