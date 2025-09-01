package nettee.series.driving.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nettee.blolet.article.readmodel.SeriesQueryModels.SeriesDetail;
import nettee.blolet.jwt.filter.annotation.AuthUser;
import nettee.blolet.jwt.filter.annotation.AuthorizedUser;
import nettee.series.application.usecase.SeriesReadUseCase;
import nettee.series.application.usecase.SeriesVisitUseCase;
import nettee.series.driving.web.dto.SeriesQueryDto.SeriesDetailResponse;
import nettee.series.driving.web.dto.SeriesQueryDto.SeriesSummaryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequiredArgsConstructor
@Tag(name = "Series", description = "Series API")
public class SeriesQueryApi {

    private final SeriesReadUseCase seriesReadUseCase;
    private final SeriesVisitUseCase visitUseCase;

    @Operation(summary = "시리즈 목록 조회", description = "블로그 ID를 기준으로 시리즈 목록을 조회합니다.")
    @ApiResponse(
            content = @Content(
                    schema = @Schema(implementation = SeriesSummaryResponse.class)
            )
    )
    @GetMapping("/blogs/{blogId}/series")
    public SeriesSummaryResponse getSeriesList(@PathVariable("blogId") String blogId) {
        return SeriesSummaryResponse.builder()
                .seriesList(seriesReadUseCase.getSeriesList(blogId))
                .build();
    }

    @Operation(summary = "시리즈 상세 조회", description = "시리즈 ID를 이용해 시리즈를 상세 조회합니다.")
    @GetMapping("/series/{seriesId}")
    public SeriesDetailResponse getSeries(
            @PathVariable("seriesId") String seriesId,
            @AuthUser Optional<AuthorizedUser> signedUser
    ) {
        AtomicReference<SeriesDetail> series = new AtomicReference<>();

        signedUser.ifPresentOrElse(
                (user) -> series.set(seriesReadUseCase.findDetailForOwner(seriesId, user.userId())),
                () -> series.set(visitUseCase.findDetailForPublic(seriesId))
        );

        return SeriesDetailResponse.builder()
                .series(series.get())
                .build();
    }
}
