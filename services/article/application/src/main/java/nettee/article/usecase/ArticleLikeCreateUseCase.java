package nettee.article.usecase;

import nettee.article.domain.ArticleLike;

public interface ArticleLikeCreateUseCase {
    
    ArticleLike createArticleLike(String profileId, String articleId);
}
