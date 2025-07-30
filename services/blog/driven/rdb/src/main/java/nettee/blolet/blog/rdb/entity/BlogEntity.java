package nettee.blolet.blog.rdb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.jpa.support.SnowflakeBaseTimeEntity;

import java.util.Objects;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogEntity extends SnowflakeBaseTimeEntity {
    private Long userId;
    private String name;
    private String url;

    // TODO enable this constructor since JPA core is fixed
//    public BlogEntity(Long id, Long userId, String name, String url, Instant createdAt, Instant updatedAt) {
//        super(id, createdAt, updatedAt);
//        this.userId = userId;
//        this.name = name;
//        this.url = url;
//    }

    @Builder(
            builderClassName = "DefaultBlogEntityUpdateBuilder",
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BlogEntity blog)) return false;

        return Objects.equals(getId(), blog.getId())
                && Objects.equals(userId, blog.getUserId())
                && Objects.equals(name, blog.getName())
                && Objects.equals(url, blog.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), userId, name, url);
    }
}
