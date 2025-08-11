package nettee.article.driving.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import nettee.article.readmodel.ArticleQueryModels.ArticleDetail;
import nettee.article.readmodel.ArticleQueryModels.ArticleSummary;

import java.util.List;

public final class ArticleQueryDto {

    private ArticleQueryDto() {}

    @Builder
    public record ArticleListViewsResponse(
            @Schema(description = "아티클 목록", example = "")
            List<ArticleSummary> articles,
            boolean hasNext
    ) {}

    @Builder
    public record ArticleDetailViewResponse(
            ArticleDetail article
    ) {}
}
