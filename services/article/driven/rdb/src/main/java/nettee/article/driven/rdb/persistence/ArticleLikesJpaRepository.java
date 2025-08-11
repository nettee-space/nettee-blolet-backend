package nettee.article.driven.rdb.persistence;

import nettee.article.driven.rdb.entity.ArticleLikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleLikesJpaRepository extends JpaRepository<ArticleLikesEntity, Long> {

    Optional<ArticleLikesEntity> findByProfileIdAndArticleId(String profileId, String articleId);

    void deleteByProfileIdAndArticleId(String profileId, String articleId);
}
