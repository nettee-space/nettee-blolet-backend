package nettee.notification.driven.rdb.entity;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.jpa.support.LongBaseTimeEntity;
import nettee.notification.driven.rdb.entity.type.NotificationEntityType;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "notification")
public class NotificationEntity extends LongBaseTimeEntity {

    public Long userId;

    public NotificationEntityType type;

    public String content;

    public boolean isRead;

    @Builder
    public NotificationEntity(Long userId, NotificationEntityType type, String content, boolean isRead) {
        this.userId = userId;
        this.type = type;
        this.content = content;
        this.isRead = isRead;
    }

    @Builder(
        builderClassName = "updateNotificationEntityBuilder",
        builderMethodName = "prepareNotificationEntityUpdate",
        buildMethodName = "update"
    )
    public void update(boolean isRead) {
        this.isRead = isRead;
    }
}
