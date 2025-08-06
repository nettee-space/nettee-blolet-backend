package nettee.article.usecase;

import nettee.article.domain.ArticleLike;

public interface ArticleLikeCreateUseCase {
    
    ArticleLike createArticleLike(String userId, String profileId, String articleId);
}
