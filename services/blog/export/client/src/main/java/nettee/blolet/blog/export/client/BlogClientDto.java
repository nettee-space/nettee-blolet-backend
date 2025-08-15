package nettee.blolet.blog.export.client;

import lombok.Builder;

public final class BlogClientDto {

    private BlogClientDto() {}

    @Builder
    public record BlogOwnershipVerifyResponse(
            Boolean isOwner
    ) {}
}
