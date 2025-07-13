package nettee.notification.driven.rdb.persistence;

import nettee.notification.application.port.NotificationQueryRepositoryPort;
import nettee.notification.driven.rdb.entity.NotificationEntity;
import nettee.notification.driven.rdb.persistence.mapper.NotificationEntityMapper;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationQueryAdapter extends QuerydslRepositorySupport implements
    NotificationQueryRepositoryPort {

    private final NotificationEntityMapper notificationMapper;

    public NotificationQueryAdapter(NotificationEntityMapper notificationEntityMapper) {
        super(NotificationEntity.class);
        this.notificationMapper = notificationEntityMapper;
    }
}
