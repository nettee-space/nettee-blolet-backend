package nettee.blolet.blog.web.dto;

import lombok.Builder;
import nettee.blolet.blog.readmodel.BlogReadModels.BlogDetail;
import nettee.blolet.blog.readmodel.BlogReadModels.UserProfileBlogs;

import java.util.Map;

public final class BlogQueryDto {

    @Builder
    public record BlogListViewResponse(
            Map<String, UserProfileBlogs> ownerProfiles
    ) {}

    @Builder
    public record BlogDetailViewResponse(
            BlogDetail blog
    ) {}

    @Builder
    public record BlogOwnershipVerifyResponse(
            Boolean isOwner
    ) {}
}
