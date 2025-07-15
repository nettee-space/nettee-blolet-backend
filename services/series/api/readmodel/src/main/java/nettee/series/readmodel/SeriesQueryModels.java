package nettee.series.readmodel;

import lombok.Builder;

import java.time.Instant;

public final class SeriesQueryModels {

    private SeriesQueryModels() {}

    @Builder
    public record SeriesSummary(
            String id,
            String blogID,
            String title,
            Integer displayOrder,
            Instant createdAt,
            Instant updatedAt
    ) {
    }
}
