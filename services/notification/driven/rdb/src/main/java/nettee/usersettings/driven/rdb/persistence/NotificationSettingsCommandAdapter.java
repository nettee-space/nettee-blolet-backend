package nettee.usersettings.driven.rdb.persistence;

import lombok.RequiredArgsConstructor;
import nettee.notification.domain.notification.type.usersettings.NotificationSettings;
import nettee.usersettings.application.port.NotificationSettingsCommandRepositoryPort;
import nettee.usersettings.driven.rdb.persistence.mapper.NotificationSettingsEntityMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import static nettee.usersettings.exception.NotificationSettingsCommandErrorCode.DEFAULT;

@Repository
@RequiredArgsConstructor
public class NotificationSettingsCommandAdapter implements
    NotificationSettingsCommandRepositoryPort {

    private final NotificationSettingsJpaRepository notificationJpaRepository;
    private final NotificationSettingsEntityMapper notificationSettingsMapper;

    @Override
    public NotificationSettings save(NotificationSettings notificationSettings) {
        var notificationSettingsEntity = notificationSettingsMapper.toEntity(notificationSettings);
        try {
            var savedSettings = notificationJpaRepository.save(notificationSettingsEntity);
            return notificationSettingsMapper.toDomain(savedSettings);
        } catch (DataAccessException e) {
            throw DEFAULT.exception(e);
        }
    }
}
