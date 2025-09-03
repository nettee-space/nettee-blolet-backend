package nettee.auth.rdb.adapter;

import lombok.RequiredArgsConstructor;
import nettee.auth.port.AuthQueryRepositoryPort;
import nettee.auth.rdb.mapper.UserEntityMapper;
import nettee.auth.rdb.repository.AuthJpaRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthQueryRepositoryAdapter implements AuthQueryRepositoryPort {

    private final AuthJpaRepository authJpaRepository;
    private final UserEntityMapper mapper;
}
