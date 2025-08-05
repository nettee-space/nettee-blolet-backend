package nettee.auth.rdb.adapter;

import lombok.RequiredArgsConstructor;
import nettee.auth.domain.User;
import nettee.auth.port.AuthCommandRepositoryPort;
import nettee.auth.rdb.entity.UserEntity;
import nettee.auth.rdb.mapper.UserEntityMapper;
import nettee.auth.rdb.repository.AuthJpaRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthCommandRepositoryAdapter implements AuthCommandRepositoryPort {

    private final AuthJpaRepository authJpaRepository;
    private final UserEntityMapper mapper;

    @Override
    public void transactional(Runnable runnable) {
        runnable.run();
    }

    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        return mapper.toDomain(authJpaRepository.save(entity));
    }
}
