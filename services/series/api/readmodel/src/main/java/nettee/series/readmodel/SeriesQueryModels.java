package nettee.series.readmodel;

import io.swagger.v3.oas.annotations.media.Schema;
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
            @Schema(
                    description = "시리즈 배너 이미지 경로",
                    example = "https://assets.blolet.com/images/series/01234567-89ab-cdef-0123-456789abcdef"
            )
            String bannerUrl,
            List<SeriesArticleSummary> articles,
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
