package nettee.notification.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nettee.notification.application.port.NotificationCommandRepositoryPort;
import nettee.notification.application.usecase.NotificationCreateUseCase;
import nettee.notification.domain.notification.Notification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationCommandService implements NotificationCreateUseCase {

    private final NotificationCommandRepositoryPort notificationCommandRepositoryPort;

    @Override
    public Notification createNotification(Notification notification) {
        return notificationCommandRepositoryPort.save(notification);
    }

}
