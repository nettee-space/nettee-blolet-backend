package nettee.auth.rdb.repository;

import nettee.auth.rdb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthJpaRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByLoginId(String loginId);
    boolean existsByLoginId(String loginId);
}