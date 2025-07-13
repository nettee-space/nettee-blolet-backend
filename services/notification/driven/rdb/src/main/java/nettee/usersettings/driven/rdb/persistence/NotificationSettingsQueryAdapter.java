package nettee.usersettings.driven.rdb.persistence;

import nettee.notification.driven.rdb.entity.NotificationEntity;
import nettee.usersettings.application.port.NotificationSettingsQueryRepositoryPort;
import nettee.usersettings.driven.rdb.persistence.mapper.NotificationSettingsEntityMapper;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationSettingsQueryAdapter extends QuerydslRepositorySupport implements
    NotificationSettingsQueryRepositoryPort {

    private final NotificationSettingsEntityMapper notificationSettingsMapper;

    public NotificationSettingsQueryAdapter(NotificationSettingsEntityMapper notificationSettingsMapper) {
        super(NotificationEntity.class);
        this.notificationSettingsMapper = notificationSettingsMapper;
    }
}
