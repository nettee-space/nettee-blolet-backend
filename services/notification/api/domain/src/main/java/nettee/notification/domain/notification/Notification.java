package nettee.notification.domain.notification;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.notification.domain.notification.type.NotificationType;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    private Long id;

    private Long userId;

    private NotificationType type;

    private String content;

    private boolean isRead;

    private Instant createdAt;

    private Instant updatedAt;
}
