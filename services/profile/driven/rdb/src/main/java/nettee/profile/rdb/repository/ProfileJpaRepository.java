package nettee.profile.rdb.repository;

import nettee.profile.rdb.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileJpaRepository extends JpaRepository<ProfileEntity, Long> {
    boolean existsByUserId(Long userId);
}
