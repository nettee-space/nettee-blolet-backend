package nettee.article.port;

import nettee.blolet.article.domain.ArticleLikes;

import java.util.Optional;

public interface ArticleLikesCommandRepositoryPort {

    Optional<ArticleLikes> findByProfileIdAndArticleId(String profileId, String articleId);

    ArticleLikes save(ArticleLikes articleLikes);

    void deleteByProfileIdAndArticleId(String profileId, String articleId);
}
