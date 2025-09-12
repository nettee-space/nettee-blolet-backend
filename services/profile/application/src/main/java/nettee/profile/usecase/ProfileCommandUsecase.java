package nettee.profile.usecase;

import nettee.profile.readmodel.ProfileCommandModels.ProfileCreateModel;

public interface ProfileCommandUsecase {
    void createProfile(String userId, ProfileCreateModel profileCreateModel);
}
