package nettee.blolet.blog.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import nettee.blolet.blog.readmodel.BlogReadModels.BlogDetail;
import nettee.blolet.blog.readmodel.BlogReadModels.UserProfileBlogs;

import java.util.List;

public final class BlogQueryDto {
    @Builder
    public record BlogOwnerProfileIds(
//            @Schema(description = "사용자 이름 목록", example = "moon,sun,ho")
            @Schema(description = "사용자 프로필 일련번호 목록", example = "1,2,3")
            List<String> userProfileIds
    ) {}

    @Builder
    public record BlogListViewResponse(
            List<UserProfileBlogs> owners
    ) {}

    @Builder
    public record BlogDetailViewResponse(
            BlogDetail blog
    ) {}
}
