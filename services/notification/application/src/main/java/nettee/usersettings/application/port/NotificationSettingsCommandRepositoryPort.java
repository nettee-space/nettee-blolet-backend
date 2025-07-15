package nettee.usersettings.application.port;

import nettee.notification.domain.notification.type.usersettings.NotificationSettings;

public interface NotificationSettingsCommandRepositoryPort {

    NotificationSettings save(NotificationSettings notificationSettings);
}
