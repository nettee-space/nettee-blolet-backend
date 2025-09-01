package nettee.blolet.blog.web.admin;

import lombok.RequiredArgsConstructor;
import nettee.blolet.blog.application.usecase.BlogReadUseCase;
import nettee.blolet.blog.web.admin.dto.BlogInternalQueryDto.BlogIdsQueryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("internal")
public class BlogInternalQueryApi {

    private final BlogReadUseCase useCase;

    @GetMapping("/users/{userId}/blog-id")
    public BlogIdsQueryResponse getBlogIds(
            @PathVariable("userId") String userId
    ) {
        return BlogIdsQueryResponse.builder()
                .blogIds(useCase.findBlogIdsByUserId(userId))
                .build();
    }
}
