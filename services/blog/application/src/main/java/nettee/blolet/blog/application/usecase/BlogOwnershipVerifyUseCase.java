package nettee.blolet.blog.application.usecase;

public interface BlogOwnershipVerifyUseCase {
    boolean verifyOwnershipByUserId(String userId, String blogId);
    boolean verifyOwnershipByProfileId(String profileId, String blogId);
}
