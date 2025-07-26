package nettee.auth.port;

import nettee.auth.domain.User;

public interface AuthCommandRepositoryPort {
    void transactional(Runnable runnable);
    void save(User user);
}
