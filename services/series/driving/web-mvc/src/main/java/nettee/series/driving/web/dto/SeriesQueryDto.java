package nettee.series.driving.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import nettee.series.readmodel.SeriesQueryModels.SeriesSummary;

public final class SeriesQueryDto {

    private SeriesQueryDto() {}

    @Builder
    public record SeriesSummaryResponse(
            @Schema(description = "시리즈 목록", example = "")
            SeriesSummary seriesSummary
    ) {
    }
}
