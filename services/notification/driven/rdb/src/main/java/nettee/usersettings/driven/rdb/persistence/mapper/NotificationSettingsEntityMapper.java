package nettee.usersettings.driven.rdb.persistence.mapper;

import nettee.notification.domain.notification.type.usersettings.NotificationSettings;
import nettee.usersettings.NotificationSettingsQueryModels.NotificationSettingsDetail;
import nettee.usersettings.driven.rdb.entity.NotificationSettingsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationSettingsEntityMapper {

    NotificationSettings toDomain(NotificationSettingsEntity entity);

    NotificationSettingsDetail toNotificationDetail(NotificationSettingsEntity entity);

    NotificationSettingsEntity toEntity(NotificationSettings notification);
}
