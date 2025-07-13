package nettee.article.driving.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nettee.article.driving.web.dto.ArticleCommandDto.ArticleUpdateCommand;
import nettee.article.driving.web.dto.ArticleCommandDto.ArticleCommandResponse;
import nettee.article.driving.web.dto.ArticleCommandDto.ArticleCreateCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("article")
@Tag(name = "Article", description = "Article API")
public class ArticleCommandApi {
    @Operation(summary = "아티클 생성", description = "블로그 ID에 해당하는 아티클을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공")
    })
    @PostMapping("/{blogId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleCommandResponse create(
            @RequestBody @Valid ArticleCreateCommand articleCreateCommand,
            @PathVariable("blogId") String blogId
            ) {
        // ...
        return null;
    }

    @Operation(summary = "아티클 수정", description = "블로그와 아티클 ID에 해당하는 아티클의 제목 혹은 내용을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @PutMapping("/{blogId}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArticleCommandResponse update(
            @RequestBody @Valid ArticleUpdateCommand articleUpdateCommand,
            @PathVariable("blogId") String blogId,
            @PathVariable("id") String id
    ) {
        // ...
        return null;
    }

    @Operation(summary = "아티클 삭제", description = "블로그와 아티클 ID에 해당하는 아티클을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @DeleteMapping("/{blogId}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArticleCommandResponse delete(
            @PathVariable("blogId") String blogId,
            @PathVariable("id") String id
    ) {
        // ...
        return null;
    }

//    @Operation(summary = "좋아요 증가", description = "블로그와 아티클 ID에 해당하는 아티클의 좋아요를 증가시킵니다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "성공")
//    })
//    @PutMapping("/{blogId}/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public ArticleCommandResponse increaseLike(
//            @PathVariable("blogId") String blogId,
//            @PathVariable("id") String id
//    ) {
//        // ...
//        return null;
//    }
//
//    @Operation(summary = "좋아요 감소", description = "블로그와 아티클 ID에 해당하는 아티클의 좋아요를 감소시킵니다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "성공")
//    })
//    @PostMapping("/{blogId}/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public ArticleCommandResponse decreaseLike(
//            @PathVariable("blogId") String blogId,
//            @PathVariable("id") String id
//    ) {
//        // ...
//        return null;
//    }

}
