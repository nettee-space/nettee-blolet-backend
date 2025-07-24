package nettee.blolet.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Objects;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogSubscription {
    private String id;
    private String userId;
    private String blogId;

    private Boolean emailAllowed;
    private Boolean notificationAllowed;

    private Instant createdAt;
    private Instant updatedAt;

    public BlogSubscription(String userId, String blogId, Boolean emailAllowed, Boolean notificationAllowed) {
        this.userId = userId;
        this.blogId = blogId;
        this.emailAllowed = emailAllowed;
        this.notificationAllowed = notificationAllowed;
    }

    public void subscribeNewsletter() {
        this.emailAllowed = true;
    }

    public void unsubscribeNewsletter() {
        this.emailAllowed = false;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BlogSubscription that)) return false;
        return Objects.equals(userId, that.userId)
                && Objects.equals(blogId, that.blogId)
                && Objects.equals(emailAllowed, that.emailAllowed)
                && Objects.equals(notificationAllowed, that.notificationAllowed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, blogId, emailAllowed, notificationAllowed);
    }
}
