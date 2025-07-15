package nettee.notification.application.port;

import nettee.notification.domain.notification.Notification;

public interface NotificationCommandRepositoryPort {

    Notification save(Notification notification);
}
