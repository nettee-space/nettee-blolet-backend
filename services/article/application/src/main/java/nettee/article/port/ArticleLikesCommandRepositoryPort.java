package nettee.article.port;

import nettee.article.domain.ArticleLikes;

public interface ArticleLikesCommandRepositoryPort {

    ArticleLikes save(ArticleLikes articleLikes);

    void deleteByProfileIdAndArticleId(String profileId, String articleId);
}
