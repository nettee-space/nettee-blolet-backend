package nettee.blole.article.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nettee.blole.article.web.dto.ArticleQueryDto.ArticleDetailViewResponse;
import nettee.blole.article.web.dto.ArticleQueryDto.ArticleListViewsResponse;
import nettee.blolet.article.application.usecase.ArticleReadUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

import static nettee.blolet.article.exception.ArticleErrorCode.ARTICLE_QUERY_SIZE_LIMIT_EXCEEDED;

@RestController
@RequiredArgsConstructor
@Tag(name = "Article", description = "Article API")
public class ArticleQueryApi {

    private final ArticleReadUseCase articleReadUseCase;

    @Operation(summary = "아티클 목록 조회", description = "블로그 ID에 해당하는 아티클 목록을 조회합니다.")
    @GetMapping("/blogs/{blogId}/articles")
    public ArticleListViewsResponse getArticles(
            @PathVariable("blogId") String blogId,
            @RequestParam("lastCreatedAt") Instant lastCreatedAt,
            @RequestParam("size") int size
    ) {
        if (size > 50) {
            throw ARTICLE_QUERY_SIZE_LIMIT_EXCEEDED.exception();
        }

        var articles = articleReadUseCase.getArticleList(blogId, lastCreatedAt, size);
        return ArticleListViewsResponse.builder()
                .articles(articles.getContent())
                .hasNext(articles.hasNext())
                .build();
    }

    @Operation(summary = "(제외 예정) 아티클 단건 조회", description = "아티클 ID에 해당하는 아티클 정보를 조회합니다.")
    @GetMapping("/articles/{articleId}")
    public ArticleDetailViewResponse getArticle(
            @PathVariable("articleId") String articleId
    ) {
        var article =articleReadUseCase.getArticle(articleId);
        return ArticleDetailViewResponse.builder()
                .article(article)
                .build();
    }
}
