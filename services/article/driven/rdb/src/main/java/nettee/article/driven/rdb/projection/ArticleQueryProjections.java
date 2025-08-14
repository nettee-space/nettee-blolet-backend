package nettee.article.driven.rdb.projection;

import lombok.Builder;
import nettee.article.driven.rdb.entity.type.builder.ArticleEntityStatus;

import java.time.Instant;

public final class ArticleQueryProjections {

    private ArticleQueryProjections() {}

    @Builder
    public record ArticleDetailProjection(
            Long id,
            Long blogId,
            String title,
            String content,
            String path,
            Integer totalViews,
            Integer totalLikes,
            Integer totalShares,
            ArticleEntityStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {}

    @Builder
    public record ArticleSummaryProjection(
            Long id,
            Long blogId,
            String title,
            ArticleEntityStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {}
}
