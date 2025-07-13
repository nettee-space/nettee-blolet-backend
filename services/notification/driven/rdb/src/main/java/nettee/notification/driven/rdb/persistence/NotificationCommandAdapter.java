package nettee.notification.driven.rdb.persistence;

import lombok.RequiredArgsConstructor;
import nettee.notification.application.port.NotificationCommandRepositoryPort;
import nettee.notification.domain.notification.Notification;
import nettee.notification.driven.rdb.persistence.mapper.NotificationEntityMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import static nettee.notification.exception.NotificationCommandErrorCode.DEFAULT;

@Repository
@RequiredArgsConstructor
public class NotificationCommandAdapter implements NotificationCommandRepositoryPort {
    private final NotificationJpaRepository notificationJpaRepository;
    private final NotificationEntityMapper notificationMapper;

    @Override
    public Notification save(Notification notification) {
        var notificationEntity = notificationMapper.toEntity(notification);
        try {
            var savedNotification = notificationJpaRepository.save(notificationEntity);
            return notificationMapper.toDomain(savedNotification);
        } catch (DataAccessException e) {
            throw DEFAULT.exception(e);
        }
    }
}
