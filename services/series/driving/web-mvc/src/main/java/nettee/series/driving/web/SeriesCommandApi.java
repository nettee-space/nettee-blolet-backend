package nettee.series.driving.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nettee.series.driving.web.dto.SeriesCommandDto.SeriesUpdateCommand;
import nettee.series.driving.web.dto.SeriesCommandDto.SeriesCommandResponse;
import nettee.series.driving.web.dto.SeriesCommandDto.SeriesCreateCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("series")
@Tag(name = "Series", description = "Series API")
public class SeriesCommandApi {
    
    @Operation(summary = "시리즈 생성", description = "시리즈를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SeriesCommandResponse create(@RequestBody @Valid SeriesCreateCommand command) {
        // ...
        return null;
    }
    
    @Operation(summary = "시리즈 수정", description = "해당 시리즈의 제목 혹은 사용자 순서를 수정합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public SeriesCommandResponse update(@RequestBody @Valid SeriesUpdateCommand command) {
        // ...
        return null;
    }
    
    @Operation(summary = "시리즈 삭제", description = "블로그와 시리즈 ID로 시리즈를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @DeleteMapping("/{blogId}/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public SeriesCommandResponse delete(@PathVariable("id") String blogId, @PathVariable("id") String id) {
        // ...
        return null;
    }
}
