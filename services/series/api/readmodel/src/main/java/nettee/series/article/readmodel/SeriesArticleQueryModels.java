package nettee.series.article.readmodel;

import lombok.Builder;

import java.time.Instant;

public final class SeriesArticleQueryModels {

    private SeriesArticleQueryModels() {}

    @Builder
    public record SeriesArticleSummary(
            String id,
            String articleId,
            String draftId,
            String articleTitle,
            Integer displayOrder,
            Instant createdAt,
            Instant updatedAt
    ) {
    }
}
