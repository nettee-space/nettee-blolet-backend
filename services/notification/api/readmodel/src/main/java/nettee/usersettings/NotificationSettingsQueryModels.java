package nettee.usersettings;

import lombok.Builder;

public class NotificationSettingsQueryModels {

    private NotificationSettingsQueryModels() {
    }

    @Builder
    public record NotificationSettingsDetail(
        Long id,
        Long userId,
        boolean onComment,
        boolean onReply,
        boolean onSubscribe,
        boolean onNewsFeed
    ){
    }
}
