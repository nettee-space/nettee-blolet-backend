package nettee.auth.rdb.mapper;

import nettee.auth.domain.User;
import nettee.auth.rdb.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserEntity toEntity(User domain);
    User toDomain(UserEntity entity);
}
