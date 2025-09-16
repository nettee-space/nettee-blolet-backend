package nettee.profile.service;

import static nettee.profile.exception.ProfileErrorCode.PROFILE_ALREADY_EXIST;

import lombok.RequiredArgsConstructor;
import nettee.profile.domain.Profile;
import nettee.profile.exception.ProfileException;
import nettee.profile.port.ProfileCommandRepositoryPort;
import nettee.profile.readmodel.ProfileCommandModels.ProfileCreateModel;
import nettee.profile.usecase.ProfileCommandUsecase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileCommandService implements ProfileCommandUsecase {

    private final ProfileCommandRepositoryPort profileCommandRepositoryPort;

    @Override
    public String createProfile(String userId, ProfileCreateModel model) {
        // 1인 1프로필 정책
        boolean exists = profileCommandRepositoryPort.existsByUserId(userId);
        if (exists) {
            throw new ProfileException(PROFILE_ALREADY_EXIST);
        }

        Profile profile = Profile.builder()
            .userId(userId)
            .job(model.job())
            .nickname(model.nickname())
            .interests(model.interests())
            .build();

        Profile saveProfile = profileCommandRepositoryPort.save(profile);
        return saveProfile.getId();
    }
}
