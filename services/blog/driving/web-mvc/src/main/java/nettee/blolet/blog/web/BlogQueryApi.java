package nettee.blolet.blog.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nettee.blolet.blog.application.usecase.BlogOwnershipVerifyUseCase;
import nettee.blolet.blog.web.dto.BlogQueryDto.BlogDetailViewResponse;
import nettee.blolet.blog.web.dto.BlogQueryDto.BlogListViewResponse;
import nettee.blolet.blog.web.dto.BlogQueryDto.BlogOwnershipVerifyResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_OWNER_ID_REQUIRED;

@RestController
@RequiredArgsConstructor
@RequestMapping("blogs")
@Tag(name = "Blog")
public class BlogQueryApi {

    private final BlogOwnershipVerifyUseCase verifyOwnershipUseCase;

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

    /**
     * TODO 예상되는 정책 또는 논의 (블로그 상세 조회)
     * <ul>
     *     <li>Q. 사용자는 최소 하나의 블로그를 갖고 있어야 할까요?</li>
     * </ul>
     */
    @GetMapping("/{blogId}")
    @Operation(
            summary = "블로그 상세 조회",
            description = "블로그 정보를 조회합니다."
    )
    public BlogDetailViewResponse findByBlogId(@PathVariable("blogId") String blogId) {
        return null;
    }

    @GetMapping("/{blogId}/ownership")
    @Operation(
            summary = "사용자의 블로그 소유권 확인",
            description = "파라미터로 전달한 사용자의 블로그 소유권을 확인합니다."
    )
    public BlogOwnershipVerifyResponse validateBlogOwnership(
            @PathVariable("blogId") String blogId,
            @RequestParam(value = "profileId", required = false) String profileId,
            @RequestParam(value = "userId", required = false) String userId
    ) {
        String ownerId = profileId != null ? profileId : userId;

        // profileId 또는 userId가 필요함.
        if (ownerId == null) {
            var cause = new NullPointerException("사용자의 `profileId` 또는 `userId`가 필요합니다.");
            throw BLOG_OWNER_ID_REQUIRED.exception(cause);
        }

        return BlogOwnershipVerifyResponse.builder()
                .isOwner(verifyOwnershipUseCase.verifyOwnershipByUserId(userId, blogId))
                .build();
    }
}
