package nettee.article.driven.rdb.persistence;

import nettee.article.driven.rdb.entity.ArticleEntity;
import nettee.article.readmodel.ArticleQueryModels.ArticleDetail;
import nettee.article.readmodel.ArticleQueryModels.ArticleSummary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;

public interface ArticleJpaRepository extends JpaRepository<ArticleEntity, Long> {

    Optional<ArticleDetail> findDetailById(Long id);

    Slice<ArticleSummary> findByBlogIdAndCreatedAtBeforeOrderByCreatedAtDesc(Long blogId, Instant createdAt, Pageable pageable);
}
