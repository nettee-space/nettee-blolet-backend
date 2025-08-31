package nettee.series.driven.rdb.persistence.projection;

import java.time.Instant;

public final class SeriesProjections {
    private SeriesProjections() {}

    public record SeriesDetailProjection(
            Long id,
            Long blogId,
            String title,
            String description,
            String bannerUrl,
            Integer displayOrder,
            Instant createdAt,
            Instant updatedAt
    ) {}

    public record SeriesArticleSummaryProjection(
            // Long seriesId,
            Long articleId,
            Long draftId,
            String currentTitle,
            Integer displayOrder,
            Instant createdAt,
            Instant updatedAt
    ) {}
}
