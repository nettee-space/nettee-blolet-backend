package nettee.auth.port;

import java.util.Optional;
import nettee.auth.domain.User;

public interface AuthQueryRepositoryPort {
    Optional<User> findById(String userId);
    Optional<User> findByLoginId(String loginId);
    boolean existsByLoginId(String loginId);

    Optional<User> findByEmail(String email);
}
