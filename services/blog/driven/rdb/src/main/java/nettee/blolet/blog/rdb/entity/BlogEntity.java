package nettee.blolet.blog.rdb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Entity
@Table(
        name = "blog",
        schema = "blog"
)
public class BlogEntity extends SnowflakeBaseTimeEntity {
    public Long userId;
    public String name;
    public String url;

    // TODO enable this constructor since JPA core is fixed
//    public BlogEntity(Long id, Long userId, String name, String url, Instant createdAt, Instant updatedAt) {
//        super(id, createdAt, updatedAt);
//        this.userId = userId;
//        this.name = name;
//        this.url = url;
//    }

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
