package nettee.blolet.blog.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nettee.blolet.blog.application.usecase.BlogOwnershipVerifyUseCase;
import nettee.blolet.blog.application.usecase.BlogReadUseCase;
import nettee.blolet.blog.web.dto.BlogQueryDto.BlogDetailViewResponse;
import nettee.blolet.blog.web.dto.BlogQueryDto.BlogListViewResponse;
import nettee.blolet.blog.web.dto.BlogQueryDto.BlogOwnershipVerifyResponse;
import nettee.blolet.blog.web.mapper.BlogDtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_NOT_IMPLEMENTED_FEATURE;
import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_OWNER_ID_REQUIRED;

@RestController
@RequiredArgsConstructor
@RequestMapping("blogs")
@Tag(name = "Blog")
public class BlogQueryApi {

    private final BlogReadUseCase readUseCase;
    private final BlogOwnershipVerifyUseCase verifyOwnershipUseCase;
    private final BlogDtoMapper mapper;

    /**
     * 사용자가 여러 블로그를 갖게 될 때 구현
     */
    @GetMapping
    @Operation(
            summary = "[Unsupported] 블로그 목록 조회 (여러 사용자)",
            description = "선택한 사용자별 블로그 목록을 조회합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "501",
                            description = "Not implemented"
                    )
            }
    )
    public BlogListViewResponse findAllByProfileIds(
            @RequestParam
            @Schema(description = "사용자 프로필 ID", example = "43876800000000001,43876800000000002,43876800000000003")
            Set<String> userProfileIds
    ) {
        throw BLOG_NOT_IMPLEMENTED_FEATURE.exception();
//        Map<String, UserProfileBlogs> ownerProfiles = readUseCase.findByUserProfileIds(userProfileIds);
//        return BlogListViewResponse.builder()
//                .ownerProfiles(ownerProfiles)
//                .build();
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
        boolean usesProfileId = profileId != null;
        String ownerId = usesProfileId ? profileId : userId;

        // Exception: profileId 또는 userId가 필요함.
        if (ownerId == null) {
            var cause = new NullPointerException("사용자의 `profileId` 또는 `userId`가 필요합니다.");
            throw BLOG_OWNER_ID_REQUIRED.exception(cause);
        }

        boolean isOwner = usesProfileId ?
                verifyOwnershipUseCase.verifyOwnershipByProfileId(ownerId, blogId) :
                verifyOwnershipUseCase.verifyOwnershipByUserId(ownerId, blogId);

        return BlogOwnershipVerifyResponse.builder()
                .isOwner(isOwner)
                .build();
    }
}
