package nettee.blolet.article.rdb.persistence;

import nettee.blolet.article.rdb.entity.ArticleEntity;
import nettee.blolet.article.rdb.projection.ArticleQueryProjections.ArticleDetailProjection;
import nettee.blolet.article.rdb.projection.ArticleQueryProjections.ArticleSummaryProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;

public interface ArticleJpaRepository extends JpaRepository<ArticleEntity, Long> {

    Optional<ArticleDetailProjection> findDetailById(Long id);

    Slice<ArticleSummaryProjection> findByBlogIdAndCreatedAtBeforeOrderByCreatedAtDesc(Long blogId, Instant createdAt, Pageable pageable);
}
