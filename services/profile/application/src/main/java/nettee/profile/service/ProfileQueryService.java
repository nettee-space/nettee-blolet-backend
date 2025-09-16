package nettee.profile.service;

import static nettee.profile.exception.ProfileErrorCode.PROFILE_NOT_FOUND;

import lombok.RequiredArgsConstructor;
import nettee.profile.domain.Profile;
import nettee.profile.port.ProfileQueryRepositoryPort;
import nettee.profile.usecase.ProfileQueryUsecase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileQueryService implements ProfileQueryUsecase {

    private final ProfileQueryRepositoryPort profileQueryRepositoryPort;

    @Override
    public String findProfileId(String userId) {
        Profile profile = profileQueryRepositoryPort.findByUserId(userId)
            .orElseThrow(PROFILE_NOT_FOUND::exception);

        return profile.getId();
    }
}
