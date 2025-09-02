package nettee.blolet.article.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nettee.blolet.article.application.usecase.SeriesCreateUseCase;
import nettee.blolet.article.application.usecase.SeriesDeleteUseCase;
import nettee.blolet.article.application.usecase.SeriesUpdateUseCase;
import nettee.blolet.article.web.dto.SeriesCommandDto.SeriesCommandResponse;
import nettee.blolet.article.web.dto.SeriesCommandDto.SeriesCreateCommand;
import nettee.blolet.article.web.dto.SeriesCommandDto.SeriesUpdateCommand;
import nettee.blolet.article.web.mapper.SeriesDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("series")
@Tag(name = "Series", description = "Series API")
public class SeriesCommandApi {
    
    private final SeriesCreateUseCase seriesCreateUseCase;
    private final SeriesUpdateUseCase seriesUpdateUseCase;
    private final SeriesDeleteUseCase seriesDeleteUseCase;
    private final SeriesDtoMapper mapper;
    
    @Operation(summary = "시리즈 생성", description = "블로그 ID에 해당하는 시리즈를 생성합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SeriesCommandResponse create(@RequestBody @Valid SeriesCreateCommand command) {
        var series = mapper.toDomain(command);
        
        return SeriesCommandResponse.builder()
                .series(seriesCreateUseCase.createSeries(series))
                .build();
    }
    
    @Operation(summary = "시리즈 수정", description = "블로그 ID에 해당 시리즈를 수정합니다")
    @PutMapping("/{seriesId}")
    @ResponseStatus(HttpStatus.OK)
    public SeriesCommandResponse update(@RequestBody @Valid SeriesUpdateCommand command, @PathVariable("seriesId") String seriesId) {
        var series = mapper.toDomain(command, seriesId);
        
        return SeriesCommandResponse.builder()
                .series(seriesUpdateUseCase.updateSeries(series))
                .build();
    }
    
    @Operation(summary = "시리즈 삭제", description = "시리즈 ID로 시리즈를 삭제합니다.")
    @DeleteMapping("/{seriesId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("seriesId") String seriesId) {
        seriesDeleteUseCase.deleteSeries(seriesId);
    }
}
