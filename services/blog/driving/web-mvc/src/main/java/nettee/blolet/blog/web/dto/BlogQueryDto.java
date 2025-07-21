package nettee.blolet.blog.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import nettee.blolet.blog.readmodel.BlogReadModels.BlogDetail;
import nettee.blolet.blog.readmodel.BlogReadModels.UserProfileBlogs;

import java.util.List;

public final class BlogQueryDto {

    @Builder
    public record BlogListViewResponse(
            List<UserProfileBlogs> owners
    ) {}

    @Builder
    public record BlogDetailViewResponse(
            BlogDetail blog
    ) {}
}
