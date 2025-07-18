package nettee.article.driving.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import nettee.article.domain.Article;

public final class ArticleCommandDto {

    private ArticleCommandDto() {}

    @Builder
    public record ArticleCreateCommand(
            @NotBlank(message = "제목을 입력하십시오.")
            @Size(max = 255, message = "제목은 255자 이내로 입력하세요.")
            @Schema(description = "아티클 제목", example = "아티클 예시")
            String title,

            @NotBlank(message = "내용을 입력하십시오.")
            @Schema(description = "아티클 내용", example = "이것은 아티클의 본문입니다.")
            String content,

            @Schema(description = "게시글 URL path", example = "/article/1")
            String path,

            @Schema(description = "상태", example = "ACTIVE")
            String status
    ) {}

    @Builder
    public record ArticleUpdateCommand(
            @NotBlank(message = "제목을 입력하십시오.")
            @Size(max = 255, message = "제목은 255자 이내로 입력하세요.")
            @Schema(description = "아티클 제목", example = "아티클 예시")
            String title,

            @NotBlank(message = "내용을 입력하십시오.")
            @Schema(description = "아티클 내용", example = "이것은 아티클의 본문입니다.")
            String content
    ) {}

    @Builder
    public record ArticleCommandResponse(
            Article article
    ) {}
}
