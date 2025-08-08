package nettee.article.port;

import nettee.article.domain.ArticleLike;

public interface ArticleLikesCommandRepositoryPort {

    ArticleLike save(String profileId, String articleId);

    void delete(String profileId, String articleId);
}
