package nettee.article.port;

import nettee.article.domain.ArticleLike;

public interface ArticleLikesCommandRepositoryPort {

    ArticleLike save(String userId, String profileId, String articleId);

    void delete(String userId, String profileId, String articleId);
}
