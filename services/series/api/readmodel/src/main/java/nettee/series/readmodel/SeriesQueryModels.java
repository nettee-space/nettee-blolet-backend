package nettee.series.readmodel;

import lombok.Builder;
import nettee.series.article.readmodel.SeriesArticleQueryModels.SeriesArticleSummary;

import java.time.Instant;
import java.util.List;

public final class SeriesQueryModels {

    private SeriesQueryModels() {}
    
    @Builder
    public record SeriesDetail(
            String id,
            String blogId,
            String title,
            String description,
            String banner,
            List<SeriesArticleSummary> seriesArticleSummaryList,
            Instant createdAt,
            Instant updatedAt
    ) {
    }
    
    @Builder
    public record SeriesSummary(
            String id,
            String blogId,
            String title,
            Integer displayOrder,
            Instant createdAt,
            Instant updatedAt
    ) {
    }
}
