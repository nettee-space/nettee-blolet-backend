package nettee.blole.article.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import nettee.blolet.article.readmodel.SeriesQueryModels.SeriesDetail;
import nettee.blolet.article.readmodel.SeriesQueryModels.SeriesSummary;

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
