package nettee.auth.rdb.adapter;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import nettee.auth.domain.User;
import nettee.auth.port.AuthQueryRepositoryPort;
import nettee.auth.rdb.entity.UserEntity;
import nettee.auth.rdb.mapper.UserEntityMapper;
import nettee.auth.rdb.repository.AuthJpaRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthQueryRepositoryAdapter implements AuthQueryRepositoryPort {

    private final AuthJpaRepository authJpaRepository;
    private final UserEntityMapper mapper;

    @Override
    public Optional<User> findByLoginId(String loginId) {
        Optional<UserEntity> userEntity = authJpaRepository.findByLoginId(loginId);
        return userEntity.map(mapper::toDomain);
    }

    @Override
    public boolean existsByLoginId(String loginId) {
        return authJpaRepository.existsByLoginId(loginId);
    }
}
