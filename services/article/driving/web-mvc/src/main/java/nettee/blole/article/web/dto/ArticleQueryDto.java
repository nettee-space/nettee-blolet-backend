package nettee.blole.article.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import nettee.blolet.article.readmodel.ArticleQueryModels.ArticleDetail;
import nettee.blolet.article.readmodel.ArticleQueryModels.ArticleSummary;

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
