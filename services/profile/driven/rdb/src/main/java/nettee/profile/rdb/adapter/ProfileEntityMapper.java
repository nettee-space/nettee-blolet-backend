package nettee.profile.rdb.adapter;

import nettee.profile.domain.Profile;
import nettee.profile.rdb.entity.ProfileEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileEntityMapper {
    ProfileEntity toEntity(Profile profile);
}
