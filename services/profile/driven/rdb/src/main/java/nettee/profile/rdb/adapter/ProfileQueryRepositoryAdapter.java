package nettee.profile.rdb.adapter;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import nettee.profile.domain.Profile;
import nettee.profile.port.ProfileQueryRepositoryPort;
import nettee.profile.rdb.entity.ProfileEntity;
import nettee.profile.rdb.repository.ProfileJpaRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileQueryRepositoryAdapter implements ProfileQueryRepositoryPort {

    private final ProfileJpaRepository profileJpaRepository;
    private final ProfileEntityMapper mapper;

    @Override
    public Optional<Profile> findByUserId(String userId) {
        Optional<ProfileEntity> profileEntity = profileJpaRepository.findByUserId(Long.valueOf(userId));
        return profileEntity.map(mapper::toDomain);
    }
}
