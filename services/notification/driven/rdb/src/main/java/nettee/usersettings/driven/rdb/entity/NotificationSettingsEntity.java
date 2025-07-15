package nettee.usersettings.driven.rdb.entity;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.jpa.support.LongBaseTimeEntity;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "notificationSettings")
public class NotificationSettingsEntity extends LongBaseTimeEntity {

    public Long userId;

    public boolean onComment;

    public boolean onReply;

    public boolean onSubscribe;

    public boolean onNewsFeed;

    @Builder
    public NotificationSettingsEntity(Long userId, boolean onComment, boolean onReply, boolean onSubscribe, boolean onNewsFeed) {
        this.userId = userId;
        this.onComment = onComment;
        this.onReply = onReply;
        this.onSubscribe = onSubscribe;
        this.onNewsFeed = onNewsFeed;
    }

    @Builder(
        builderClassName = "updateNotificationSettingsEntityBuilder",
        builderMethodName = "prepareNotificationSettingsEntityUpdate",
        buildMethodName = "update"
    )
    public void update(boolean onComment, boolean onReply, boolean onSubscribe, boolean onNewsFeed) {
        this.onComment = onComment;
        this.onReply = onReply;
        this.onSubscribe = onSubscribe;
        this.onNewsFeed = onNewsFeed;
    }
}
