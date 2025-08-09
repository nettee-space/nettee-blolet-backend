package nettee.blolet.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Objects;

import static nettee.common.validation.Preconditions.validateNotBlank;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    private String id;
    private String userId;
    private String profileId;
    private String username;
    private String nickname;
    private String name;
    private String urlIdentifier;
    private Instant createdAt;
    private Instant updatedAt;

    public Blog(
            String userId,
            String profileId,
            String username,
            String nickname,
            String name,
            String urlIdentifier
    ) {
        this.userId = userId;
        this.profileId = profileId;
        this.username = username;
        this.nickname = nickname;
        this.name = name;
        this.urlIdentifier = urlIdentifier;
    }

    @Builder(
            builderClassName = "DefaultBlogUpdateBuilder",
            builderMethodName = "prepareUpdate",
            buildMethodName = "update"
    )
    public void update(String name, String url) {
        Objects.requireNonNull(name, "Name must not be null");

        this.name = name;
        if (url != null) {
            this.urlIdentifier = url;
        }
    }

    @Builder(
            builderClassName = "BlogUserProfileUpdateBuilder",
            builderMethodName = "prepareUserProfileUpdate",
            buildMethodName = "update"
    )
    public void updateUserProfile(String username, String nickname) {
        if (username != null) {
            this.username = username;
        }
        if (nickname != null) {
            this.nickname = nickname;
        }
    }

    public void updateUrl(String urlIdentifier) {
        Objects.requireNonNull(urlIdentifier, "Url must not be null");
        this.urlIdentifier = urlIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Blog blog)) return false;

        return Objects.equals(id, blog.id)
                && Objects.equals(userId, blog.userId)
                && Objects.equals(profileId, blog.profileId)
                && Objects.equals(name, blog.name)
                && Objects.equals(urlIdentifier, blog.urlIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, profileId, name, urlIdentifier);
    }
}
