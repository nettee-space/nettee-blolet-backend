package nettee.article.usecase;

import nettee.article.domain.ArticleLike;

public interface ArticleLikeDeleteUseCase {

    ArticleLike deleteArticleLike(String userId, String profileId, String articleId);
}
