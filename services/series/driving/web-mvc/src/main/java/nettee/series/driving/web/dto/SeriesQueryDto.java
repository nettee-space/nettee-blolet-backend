package nettee.series.driving.web.dto;

import lombok.Builder;
import nettee.series.readmodel.SeriesQueryModels.SeriesSummary;

public final class SeriesQueryDto {

    private SeriesQueryDto() {}

    @Builder
    public record SeriesDetailResponse(
        SeriesSummary seriesSummary
    ) {
    }
}
