package nettee.usersettings.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nettee.notification.domain.notification.type.usersettings.NotificationSettings;
import nettee.usersettings.application.port.NotificationSettingsCommandRepositoryPort;
import nettee.usersettings.application.usecase.NotificationSettingsCreateUseCase;
import nettee.usersettings.application.usecase.NotificationSettingsUpdateUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationSettingsCommandService implements NotificationSettingsCreateUseCase,
    NotificationSettingsUpdateUseCase {

    private final NotificationSettingsCommandRepositoryPort settingsCommandRepositoryPort;

    @Override
    public NotificationSettings createNotificationSettings(NotificationSettings settings) {
        return settingsCommandRepositoryPort.save(settings);
    }

    @Override
    public NotificationSettings updateNotificationSettings(NotificationSettings settings) {
        return null;
    }
}
