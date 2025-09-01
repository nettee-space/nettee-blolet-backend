package nettee.blolet.blog.web.admin.dto;

import lombok.Builder;

import java.util.Set;

public final class BlogInternalQueryDto {

    private BlogInternalQueryDto() {}

    @Builder
    public record BlogIdsQueryResponse(
            Set<String> blogIds
    ) {}
}
