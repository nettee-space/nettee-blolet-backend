package nettee.auth.port;

import nettee.auth.domain.User;

public interface AuthCommandRepositoryPort {
    void transactional(Runnable runnable);
    User save(User user);

    void deleteById(String userId);
}
