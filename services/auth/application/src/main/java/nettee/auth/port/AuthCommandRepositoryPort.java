package nettee.auth.port;

import java.util.Optional;
import nettee.auth.domain.User;

public interface AuthCommandRepositoryPort {
    void transactional(Runnable runnable);
    User save(User user);

    void deleteById(String userId);

    void updatePassword(User user);

    // 데이터 정합성이 중요한 경우에 사용
    Optional<User> findById(String userId);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
