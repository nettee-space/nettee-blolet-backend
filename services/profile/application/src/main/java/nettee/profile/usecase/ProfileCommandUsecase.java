package nettee.profile.usecase;

import nettee.profile.readmodel.ProfileCommandModels.ProfileCreateModel;

public interface ProfileCommandUsecase {
    String createProfile(String userId, ProfileCreateModel profileCreateModel);
}
