package nettee.article.driving.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nettee.article.driving.web.dto.ArticleCommandDto.ArticleUpdateCommand;
import nettee.article.driving.web.dto.ArticleCommandDto.ArticleCommandResponse;
import nettee.article.driving.web.dto.ArticleCommandDto.ArticleCreateCommand;
import nettee.article.usecase.ArticleCreateUseCase;
import nettee.article.usecase.ArticleDeleteUseCase;
import nettee.article.usecase.ArticleUpdateUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("articles")
@Tag(name = "Article", description = "Article API")
public class ArticleCommandApi {

    private final ArticleCreateUseCase articleCreateUseCase;
    private final ArticleUpdateUseCase articleUpdateUseCase;
    private final ArticleDeleteUseCase articleDeleteUseCase;
    private final ArticleDtoMapper mapper;

    @Operation(summary = "아티클 생성", description = "블로그 ID에 해당하는 아티클을 생성합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleCommandResponse create(
            @RequestBody @Valid ArticleCreateCommand articleCreateCommand
    ) {
        var article = mapper.toDomain(articleCreateCommand);
        return ArticleCommandResponse.builder()
                .article(articleCreateUseCase.createArticle(article))
                .build();
    }

    @Operation(summary = "아티클 수정", description = "블로그와 아티클 ID에 해당하는 아티클의 제목 혹은 내용을 수정합니다.")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArticleCommandResponse update(
            @RequestBody @Valid ArticleUpdateCommand articleUpdateCommand,
            @PathVariable("id") String id
    ) {
        var article = mapper.toDomain(id, articleUpdateCommand);

        return ArticleCommandResponse.builder()
                .article(articleUpdateUseCase.updateArticle(article))
                .build();
    }

    @Operation(summary = "아티클 삭제", description = "블로그와 아티클 ID에 해당하는 아티클을 삭제합니다.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(
            @PathVariable("id") String id
    ) {
        articleDeleteUseCase.deleteArticle(id);
    }

    @Operation(summary = "좋아요 증가", description = "블로그와 아티클 ID에 해당하는 아티클의 좋아요를 증가시킵니다.")
    @PostMapping("/{blogId}/{id}/like")
    @ResponseStatus(HttpStatus.OK)
    public ArticleCommandResponse increaseLike(
            @PathVariable("blogId") String blogId,
            @PathVariable("id") String id
    ) {
        // ...
        return null;
    }

    @Operation(summary = "좋아요 감소", description = "블로그와 아티클 ID에 해당하는 아티클의 좋아요를 감소시킵니다.")
    @DeleteMapping("/{blogId}/{id}/like")
    @ResponseStatus(HttpStatus.OK)
    public ArticleCommandResponse decreaseLike(
            @PathVariable("blogId") String blogId,
            @PathVariable("id") String id
    ) {
        // ...
        return null;
    }

}
