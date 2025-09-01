package nettee.blole.article.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nettee.blole.article.web.dto.ArticleCommandDto.ArticleCommandResponse;
import nettee.blole.article.web.dto.ArticleCommandDto.ArticleCreateCommand;
import nettee.blole.article.web.dto.ArticleCommandDto.ArticleUpdateCommand;
import nettee.blole.article.web.dto.ArticleLikesCommandDto.ArticleLikesCommandResponse;
import nettee.blole.article.web.dto.ArticleLikesCommandDto.ArticleLikesCreateCommand;
import nettee.blolet.article.application.usecase.ArticleCreateUseCase;
import nettee.blolet.article.application.usecase.ArticleDeleteUseCase;
import nettee.blolet.article.application.usecase.ArticleLikesDeleteUseCase;
import nettee.blolet.article.application.usecase.ArticleLikesUpdateUseCase;
import nettee.blolet.article.application.usecase.ArticleUpdateUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private final ArticleLikesUpdateUseCase articleLikesUpdateUseCase;
    private final ArticleLikesDeleteUseCase articleLikesDeleteUseCase;

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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable("id") String id
    ) {
        articleDeleteUseCase.deleteArticle(id);
    }

    @Operation(summary = "좋아요 증가", description = "프로필 ID와 아티클 ID에 해당하는 아티클 좋아요를 수정합니다.")
    @PutMapping("/{articleId}/likes")
    @ResponseStatus(HttpStatus.OK)
    public ArticleLikesCommandResponse updateLikes(
            @RequestBody @Valid ArticleLikesCreateCommand articleLikesCreateCommand,
            // TODO 추후 인가 필터 작업 완료시 userId 제거
            @RequestParam("userId") String userId,
            @PathVariable("articleId") String articleId
    ) {
        var articleLikes = mapper.toLikesDomain(articleId, userId, articleLikesCreateCommand);

        return ArticleLikesCommandResponse.builder()
                .articleLikes(articleLikesUpdateUseCase.updateArticleLikes(articleLikes))
                .build();
    }

    @Operation(summary = "좋아요 감소", description = "프로필 ID와 아티클 ID에 해당하는 아티클 좋아요를 삭제합니다.")
    @DeleteMapping("/{articleId}/likes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLikes(
            @RequestParam("profileId") String profileId,
            @PathVariable("articleId") String articleId
    ) {
        // TODO validate(검증): loginUser.profileIds().contains(profileId)

        articleLikesDeleteUseCase.deleteArticleLikes(profileId, articleId);
    }

}
