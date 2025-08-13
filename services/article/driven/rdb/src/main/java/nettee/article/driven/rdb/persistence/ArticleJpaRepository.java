package nettee.article.driven.rdb.persistence;

import nettee.article.driven.rdb.entity.ArticleEntity;
import nettee.article.driven.rdb.projection.ArticleQueryProjections.ArticleDetailProjection;
import nettee.article.driven.rdb.projection.ArticleQueryProjections.ArticleSummaryProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;

public interface ArticleJpaRepository extends JpaRepository<ArticleEntity, Long> {

    Optional<ArticleDetailProjection> findDetailById(Long id);

    Slice<ArticleSummaryProjection> findByBlogIdAndCreatedAtBeforeOrderByCreatedAtDesc(Long blogId, Instant createdAt, Pageable pageable);
}
