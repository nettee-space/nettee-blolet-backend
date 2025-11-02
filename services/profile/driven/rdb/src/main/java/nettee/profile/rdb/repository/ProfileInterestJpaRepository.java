package nettee.profile.rdb.repository;

import nettee.profile.rdb.entity.ProfileInterestEntity;
import nettee.profile.rdb.entity.key.ProfileInterestId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileInterestJpaRepository extends JpaRepository<ProfileInterestEntity, ProfileInterestId> {
}
