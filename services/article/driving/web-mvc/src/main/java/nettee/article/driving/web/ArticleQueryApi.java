package nettee.article.driving.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nettee.article.driving.web.dto.ArticleQueryDto.ArticleResponse;
import nettee.article.driving.web.dto.ArticleQueryDto.ArticleSummaryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("article")
@Tag(name = "Article", description = "Article API")
public class ArticleQueryApi {

    @Operation(summary = "아티클 목록 조회", description = "블로그 ID에 해당하는 아티클 목록을 조회합니다.")
    @GetMapping("/{blogId}")
    public List<ArticleSummaryResponse> getArticles(@PathVariable("blogId") String blogId) {
        // ...
        return null;
    }

    @Operation(summary = "아티클 단건 조회", description = "블로그와 아티클 ID에 해당하는 아티클 정보를 조회합니다.")
    @GetMapping("/{blogId}/{id}")
    public List<ArticleResponse> getArticle(
            @PathVariable("blogId") String blogId,
            @PathVariable("id") String id
    ) {
        // ...
        return null;
    }
}
