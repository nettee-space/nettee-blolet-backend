package nettee.blolet.article.rdb.persistence;

import nettee.blolet.article.rdb.entity.ArticleLikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ArticleLikesJpaRepository extends JpaRepository<ArticleLikesEntity, Long> {

    Optional<ArticleLikesEntity> findByProfileIdAndArticleId(Long profileId, Long articleId);

    @Transactional
    void deleteByProfileIdAndArticleId(Long profileId, Long articleId);
}
