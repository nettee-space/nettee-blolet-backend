package nettee.series.driving.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import nettee.series.domain.Series;

public final class SeriesCommandDto {

    private SeriesCommandDto() {}

    @Builder
    public record SeriesCreateCommand(
            @NotBlank(message = "제목을 입력하십시오.")
            @Size(max = 300, message = "제목은 300자 이내로 입력하세요.")
            @Schema(description = "시리즈 제목", example = "시리즈 예시")
            String title,
            
            @NotNull(message = "사용자 정렬 순서를 입력하세요.")
            @Schema(description = "시리즈 사용자 순서", example = "1")
            Integer displayOrder
    ) {
    }

    @Builder
    public record SeriesUpdateCommand(
            @NotNull(message = "id를 입력하십시오.")
            @Schema(description = "시리즈 ID", example = "1")
            String id,
            
            @NotBlank(message = "제목을 입력하십시오.")
            @Size(max = 300, message = "제목은 300자 이내로 입력하세요.")
            @Schema(description = "시리즈 제목", example = "시리즈 예시")
            String title,
            
            @NotNull(message = "사용자 정렬 순서를 입력하세요.")
            @Schema(description = "시리즈 사용자 순서", example = "1")
            Integer displayOrder
    ) {
    }

    @Builder
    public record SeriesCommandResponse(
            Series Series
    ) {
    }
}
