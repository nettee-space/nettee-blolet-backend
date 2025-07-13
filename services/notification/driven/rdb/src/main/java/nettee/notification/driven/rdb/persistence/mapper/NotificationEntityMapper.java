package nettee.notification.driven.rdb.persistence.mapper;

import nettee.notification.NotificationQueryModels.NotificationDetail;
import nettee.notification.domain.notification.Notification;
import nettee.notification.driven.rdb.entity.NotificationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationEntityMapper {

    Notification toDomain(NotificationEntity entity);

    NotificationDetail toNotificationDetail(NotificationEntity entity);

    NotificationEntity toEntity(Notification notification);
}
