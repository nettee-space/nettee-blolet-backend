package nettee.profile.service;

import static nettee.profile.exception.ProfileErrorCode.PROFILE_ALREADY_EXIST;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import nettee.profile.domain.Profile;
import nettee.profile.exception.ProfileException;
import nettee.profile.port.ProfileCommandRepositoryPort;
import nettee.profile.readmodel.ProfileCommandModels.ProfileCreateModel;
import nettee.profile.usecase.ProfileCommandUsecase;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class ProfileCommandService implements ProfileCommandUsecase {

    private final ProfileCommandRepositoryPort profileCommandRepositoryPort;
    private final static String DEFAULT_NICKNAME_PREFIX = "user_";

    @Override
    public void createProfile(String userId, ProfileCreateModel model) {
        // 1인 1프로필 정책
        boolean exists = profileCommandRepositoryPort.existsByUserId(userId);
        if (exists) {
            throw new ProfileException(PROFILE_ALREADY_EXIST);
        }

        // 닉네임이 비어있으면 랜덤 닉네임 생성
        String nickname = model.nickname();
        if (!StringUtils.hasText(nickname)) {
            nickname = DEFAULT_NICKNAME_PREFIX + UUID.randomUUID().toString().substring(0, 6);
        }

        Profile profile = Profile.builder()
            .userId(userId)
            .job(model.job())
            .nickname(nickname)
            .interests(model.interests())
            .build();

        profileCommandRepositoryPort.save(profile);
    }
}
