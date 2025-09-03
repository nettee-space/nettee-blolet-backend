package nettee.auth.rdb.repository;

import java.util.Optional;
import nettee.auth.rdb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthJpaRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String email);
}