package nettee.article.driving.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import nettee.article.domain.ArticleLikes;

public final class ArticleLikesCommandDto {

    private ArticleLikesCommandDto() {}

    @Builder
    public record ArticleLikesCreateCommand(
            @NotBlank(message = "프로필 ID를 입력하십시오.")
            @Schema(description = "프로필 ID", example = "1")
            String profileId,

            @NotNull(message = "좋아요 수를 입력하십시오.")
            @Min(value = 1, message = "좋아요 수는 1 이상이어야 합니다.")
            @Schema(description = "좋아요 수", example = "1")
            Integer count
    ) {}

    @Builder
    public record ArticleLikesCommandResponse(
            ArticleLikes articleLikes
    ) {}
}
