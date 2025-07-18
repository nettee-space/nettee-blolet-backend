package nettee.usersettings.application.usecase;

import nettee.notification.domain.notification.type.usersettings.NotificationSettings;

public interface NotificationSettingsUpdateUseCase {
    NotificationSettings updateNotificationSettings(NotificationSettings settings);
}
