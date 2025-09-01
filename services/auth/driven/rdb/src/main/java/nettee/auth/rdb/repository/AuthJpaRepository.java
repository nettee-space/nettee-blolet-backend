package nettee.auth.rdb.repository;

import java.util.Optional;
import nettee.auth.rdb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByLoginId(String loginId);
    boolean existsByLoginId(String loginId);
    Optional<UserEntity> findByEmail(String email);
}