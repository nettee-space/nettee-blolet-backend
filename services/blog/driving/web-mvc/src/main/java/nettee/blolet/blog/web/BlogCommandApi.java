package nettee.blolet.blog.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nettee.blolet.blog.application.usecase.BlogDeleteUseCase;
import nettee.blolet.blog.application.usecase.BlogUpdateUseCase;
import nettee.blolet.blog.web.dto.BlogCommandDto.BlogNewsletterSubscribeResponse;
import nettee.blolet.blog.web.dto.BlogCommandDto.BlogNewsletterUnsubscribeResponse;
import nettee.blolet.blog.web.dto.BlogCommandDto.BlogSubscribeResponse;
import nettee.blolet.blog.web.dto.BlogCommandDto.BlogUnsubscribeResponse;
import nettee.blolet.blog.web.dto.BlogCommandDto.BlogUpdateCommand;
import nettee.blolet.blog.web.dto.BlogCommandDto.BlogUpdateResponse;
import nettee.blolet.blog.web.mapper.BlogDtoMapper;
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
@RequestMapping("blogs")
@Tag(name = "Blog", description = "Blog API")
public class BlogCommandApi {

    private final BlogUpdateUseCase updateUseCase;
    private final BlogDeleteUseCase deleteUseCase;
    private final BlogDtoMapper mapper;

    @PutMapping("/{blogId}")
    @Operation(
            summary = "블로그 정보 수정",
            description = "사용자가 소유한 블로그 정보를 수정합니다."
    )
    public BlogUpdateResponse updateBlog(@PathVariable("blogId") String blogId, @RequestBody BlogUpdateCommand dto) {
        var domain = mapper.toDomain(blogId, dto);
        return mapper.toResponse(updateUseCase.update(domain));
    }

    @DeleteMapping("/{blogId}")
    @Operation(
            summary = "블로그 삭제",
            description = "사용자가 소유한 블로그 중 하나를 삭제합니다."
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlog(@PathVariable("blogId") String blogId) {
        deleteUseCase.deleteById(blogId);
    }

    @PostMapping("/{blogId}/subscribe")
    @Operation(
            summary = "블로그 구독(팔로우; 용어 미정)",
            description = "블로그를 구독합니다."
    )
    public BlogSubscribeResponse subscribeBlog(@PathVariable("blogId") String blogId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @DeleteMapping("/{blogId}/subscribe")
    @Operation(
            summary = "블로그 구독 취소",
            description = "구독한 블로그의 구독을 취소합니다. (TODO 정책 논의: 아마도 뉴스레터 구독도 함께 취소될 것입니다.)"
    )
    public BlogUnsubscribeResponse unsubscribeBlog(@PathVariable("blogId") String blogId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @PostMapping("/{blogId}/newsletter")
    @Operation(
            summary = "블로그 뉴스레터 구독 (뉴스레터 수신 동의)",
            description = "블로그의 뉴스레터 수신을 동의합니다. (TODO 정책 논의: 아직 구독하지 않은 블로그라면 아마도 구독도 함께 될 것입니다.)"
    )
    public BlogNewsletterSubscribeResponse subscribeNewsletter(@PathVariable("blogId") String blogId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @DeleteMapping("/{blogId}/newsletter")
    @Operation(
            summary = "블로그 뉴스레터 취소 (뉴스레터 수신 비동의)",
            description = "블로그의 뉴스레터 수신 동의를 철회합니다."
    )
    public BlogNewsletterUnsubscribeResponse unsubscribeNewsletter(@PathVariable("blogId") String blogId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
