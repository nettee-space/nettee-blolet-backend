package nettee.blolet.blog.export.client.api;

import nettee.blolet.blog.export.client.api.BlogClientDto.BlogOwnershipVerifyResponse;

public interface BlogClient {
    BlogOwnershipVerifyResponse verifyOwnership(String userId, String blogId);
    BlogOwnershipVerifyResponse verifyOwnershipFresh(String userId, String blogId);
    BlogOwnershipVerifyResponse verifyOwnershipByProfileId(String profileId, String blogId);
    BlogOwnershipVerifyResponse verifyOwnershipByProfileIdFresh(String profileId, String blogId);
}
