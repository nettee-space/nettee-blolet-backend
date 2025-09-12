package nettee.profile.rdb.repository;

import nettee.profile.rdb.entity.InterestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestJpaRepository extends JpaRepository<InterestEntity, Long> {
}
