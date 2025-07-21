package nettee.blolet.blog.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nettee.blolet.blog.web.dto.BlogQueryDto.BlogDetailViewResponse;
import nettee.blolet.blog.web.dto.BlogQueryDto.BlogListViewResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("blogs")
@Tag(name = "Blog")
public class BlogQueryApi {

    /**
     * TODO 예상되는 정책 또는 논의 (블로그 목록 조회)
     * <ul>
     *     <li>Q. 사용자는 여러 블로그를 가질 수 있을까요?</li>
     * </ul>
     */
    @GetMapping
    @Operation(
            summary = "블로그 목록 조회 (여러 사용자)",
            description = "선택한 사용자별 블로그 목록을 조회합니다."
    )
    public BlogListViewResponse findAllByUsernames(
            @RequestParam
            @Schema(description = "사용자 프로필 일련번호 목록", example = "1,2,3")
            List<String> userProfileIds
    ) {
        return null;
    }

    @GetMapping("/{blogId}")
    @Operation(
            summary = "블로그 상세 조회",
            description = "블로그 정보를 조회합니다."
    )
    public BlogDetailViewResponse findByBlogId(@PathVariable("blogId") String blogId) {
        return null;
    }
}
