package nettee.notification.domain.notification.type.usersettings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationSettings {

    private Long id;

    private Long userId;

    private boolean onComment;

    private boolean onReply;

    private boolean onSubscribe;

    private boolean onNewsFeed;
}
