package nettee.profile.rdb.repository;

import java.util.Optional;
import nettee.profile.rdb.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileJpaRepository extends JpaRepository<ProfileEntity, Long> {
    boolean existsByUserId(Long userId);

    Optional<ProfileEntity> findByUserId(Long userId);
}
