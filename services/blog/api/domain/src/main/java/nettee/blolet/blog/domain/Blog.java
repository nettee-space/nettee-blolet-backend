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
public class Blog {
    private String id;
    private String userId;
    private String name;
    private String url;
    private Instant createdAt;
    private Instant updatedAt;

    public Blog(String userId, String name, String url) {
        this.userId = userId;
        this.name = name;
        this.url = url;
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
            this.url = url;
        }
    }

    public void updateUrl(String url) {
        Objects.requireNonNull(url, "Url must not be null");
        this.url = url;
    }
}
