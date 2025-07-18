package nettee.notification.application.service;

import lombok.RequiredArgsConstructor;
import nettee.notification.application.port.NotificationQueryRepositoryPort;
import nettee.usersettings.application.port.NotificationSettingsQueryRepositoryPort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationQueryService {

    private final NotificationQueryRepositoryPort notificationQueryRepositoryPort;
    private final NotificationSettingsQueryRepositoryPort settingsQueryRepositoryPort;
}
