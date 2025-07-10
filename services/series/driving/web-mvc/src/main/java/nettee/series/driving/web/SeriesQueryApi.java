package nettee.series.driving.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nettee.series.driving.web.dto.SeriesQueryDto.SeriesSummaryResponse;
import nettee.series.readmodel.SeriesQueryModels.SeriesSummary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("series")
@Tag(name = "Series", description = "Series API")
public class SeriesQueryApi {
    
    @Operation(summary = "시리즈 목록 조회", description = "블로그 ID를 이용해 시리즈 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = SeriesSummary.class))))
    })
    @GetMapping("/{blogId}")
    public List<SeriesSummaryResponse> getSeries(@PathVariable("blogId") String blogId) {
        // ...
        return null;
    }
}
