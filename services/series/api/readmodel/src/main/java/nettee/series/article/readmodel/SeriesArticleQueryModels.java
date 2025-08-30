package nettee.series.article.readmodel;

import lombok.Builder;

import java.time.Instant;

public final class SeriesArticleQueryModels {

    private SeriesArticleQueryModels() {}

    @Builder
    public record SeriesArticleSummary(
            String seriesId,
            String articleId,
            String draftId,
            String title,
            Integer displayOrder,
            Instant createdAt,
            Instant updatedAt
    ) {
    }
}
