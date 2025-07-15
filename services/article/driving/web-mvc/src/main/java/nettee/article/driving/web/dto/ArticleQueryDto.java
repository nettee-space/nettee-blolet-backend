package nettee.article.driving.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import nettee.article.domain.Article;
import nettee.article.readmodel.ArticleQueryModels.ArticleSummary;

public final class ArticleQueryDto {

    private ArticleQueryDto() {}

    @Builder
    public record ArticleSummaryResponse(
            @Schema(description = "아티클 목록", example = "")
            ArticleSummary articleSummary
    ) {}

    @Builder
    public record ArticleResponse(
            Article article
    ) {}
}
