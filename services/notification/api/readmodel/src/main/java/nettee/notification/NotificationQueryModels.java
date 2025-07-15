package nettee.notification;

import java.time.Instant;
import lombok.Builder;
import nettee.notification.domain.notification.type.NotificationType;

public final class NotificationQueryModels {

    private NotificationQueryModels() {
    }

    @Builder
    public record NotificationDetail(
        Long id,
        Long userId,
        NotificationType type,
        String content,
        boolean isRead,
        Instant createdAt,
        Instant updatedAt
    ){
    }
}
