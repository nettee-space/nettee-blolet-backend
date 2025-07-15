package nettee.usersettings.application.usecase;

import nettee.notification.domain.notification.type.usersettings.NotificationSettings;

public interface NotificationSettingsCreateUseCase {
    NotificationSettings createNotificationSettings(NotificationSettings settings);
}
