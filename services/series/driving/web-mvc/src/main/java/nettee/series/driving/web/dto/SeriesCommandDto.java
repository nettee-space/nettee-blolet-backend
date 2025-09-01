package nettee.series.driving.web.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import nettee.blolet.article.domain.Series;
import nettee.series.article.driving.web.dto.SeriesArticleCommandDto.SeriesArticleCreateCommand;

import java.util.List;

public final class SeriesCommandDto {
    
    private SeriesCommandDto() {
    }
    
    @Builder
    public record SeriesCreateCommand(
            @NotNull(message = "블로그 ID를 입력하십시오.")
            @Schema(description = "블로그 ID", example = "1")
            String blogId,

            @NotBlank(message = "이름을 입력해주세요.")
            @Size(max = 30, message = "이름은 최대 30글자 이대로 적어주세요")
            @Schema(description = "시리즈 이름", example = "시리즈샘플")
            String title,
            
            @Schema(description = "시리즈 설명", example = "시리즈 설명 샘플입니다.")
            String description,
            
            @Schema(
                    description = "시리즈 이미지 배너 (Base64 인코딩된 문자열)",
                    example = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAA..."
            )
            String banner,
            
            @ArraySchema(
                    schema = @Schema(implementation = SeriesArticleCreateCommand.class),
                    arraySchema = @Schema(description = "시리즈에 포함된 게시글 목록")
            )
            List<SeriesArticleCreateCommand> articles
    ) {
    }
    
    @Builder
    public record SeriesUpdateCommand(
            @NotBlank(message = "이름을 입력해주세요.")
            @Size(max = 30, message = "이름은 최대 30글자 이대로 적어주세요")
            @Schema(description = "시리즈 이름", example = "시리즈샘플")
            String title,
            
            @Schema(description = "시리즈 설명", example = "시리즈 설명 샘플입니다.")
            String description,

            @Schema(
                    description = "시리즈 이미지 배너 (Base64 인코딩된 문자열)",
                    example = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAA..."
            )
            String banner,

            @ArraySchema(
                    schema = @Schema(implementation = SeriesArticleCreateCommand.class),
                    arraySchema = @Schema(description = "시리즈에 포함된 게시글 목록")
            )
            List<SeriesArticleCreateCommand> articles
    ) {
    }
    
    @Builder
    public record SeriesCommandResponse(
            Series series
    ) {
    }
}
