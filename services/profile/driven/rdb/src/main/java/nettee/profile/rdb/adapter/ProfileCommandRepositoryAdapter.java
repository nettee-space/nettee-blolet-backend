package nettee.profile.rdb.adapter;

import lombok.RequiredArgsConstructor;
import nettee.profile.domain.Profile;
import nettee.profile.port.ProfileCommandRepositoryPort;
import nettee.profile.rdb.entity.ProfileEntity;
import nettee.profile.rdb.entity.ProfileInterestEntity;
import nettee.profile.rdb.repository.ProfileInterestJpaRepository;
import nettee.profile.rdb.repository.ProfileJpaRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileCommandRepositoryAdapter implements ProfileCommandRepositoryPort {

    private final ProfileJpaRepository profileJpaRepository;
    private final ProfileInterestJpaRepository profileInterestJpaRepository;

    private final ProfileEntityMapper mapper;

    @Override
    public void save(Profile profile) {
        ProfileEntity entity = mapper.toEntity(profile);

        // profile id를 얻기 위해 저장
        ProfileEntity profileEntity = profileJpaRepository.save(entity);

        for (String interest : profile.getInterests()) {
            Long profileId = profileEntity.getId();
            Long interestId = Long.valueOf(interest);

            // 중간 테이블에 저장
            profileInterestJpaRepository.save(new ProfileInterestEntity(profileId, interestId));
        }
    }

    @Override
    public boolean existsByUserId(String userId) {
        return profileJpaRepository.existsByUserId(Long.valueOf(userId));
    }
}
