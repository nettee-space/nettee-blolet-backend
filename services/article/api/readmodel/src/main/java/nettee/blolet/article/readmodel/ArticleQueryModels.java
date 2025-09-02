package nettee.blolet.article.readmodel;

import lombok.Builder;
import nettee.blolet.article.domain.sub.ArticleStatus;

import java.time.Instant;

public final class ArticleQueryModels {

    private ArticleQueryModels() {}

    @Builder
    public record ArticleDetail(
            String id,
            String blogId,
            String draftId,
            String title,
            String content,
            String path,
            Integer totalViews,
            Integer totalLikes,
            Integer totalShares,
            ArticleStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {}

    @Builder
    public record ArticleSummary(
            String id,
            String blogId,
            String title,
            ArticleStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {}
}
