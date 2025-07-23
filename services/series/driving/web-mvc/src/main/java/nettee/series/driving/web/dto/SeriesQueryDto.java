package nettee.series.driving.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import nettee.series.readmodel.SeriesQueryModels.SeriesDetail;
import nettee.series.readmodel.SeriesQueryModels.SeriesSummary;

import java.util.List;

public final class SeriesQueryDto {

    private SeriesQueryDto() {}
    
    @Builder
    public record SeriesDetailResponse(
            @Schema(description = "시리즈")
            SeriesDetail series
    ) {
    }
    
    @Builder
    public record SeriesSummaryResponse(
            @Schema(description = "시리즈 목록")
            List<SeriesSummary> seriesList
    ) {
    }
}
