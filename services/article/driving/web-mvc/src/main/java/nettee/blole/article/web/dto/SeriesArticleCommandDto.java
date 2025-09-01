package nettee.blole.article.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public final class SeriesArticleCommandDto {
    
    private SeriesArticleCommandDto() {
    }
    
    @Builder
    public record SeriesArticleCreateCommand(
            @Schema(description = "임시글 ID", example = "1")
            String draftId,
            
            @Schema(description = "게시글 ID", example = "1")
            String articleId,
            
            @Schema(description = "전시 순서", example = "1")
            Integer displayOrder
    ) {
    }
}
