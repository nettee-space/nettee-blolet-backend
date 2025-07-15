package nettee.notification.application.usecase;

import nettee.notification.domain.notification.Notification;

public interface NotificationCreateUseCase {
    Notification createNotification(Notification notification);
}
