package nettee.blolet.blog.rdb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.jpa.support.SnowflakeBaseTimeEntity;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(
        name = "blog",
        schema = "blog"
)
public class BlogEntity extends SnowflakeBaseTimeEntity {
    public Long userId;
    public Long profileId;
    public String username;
    public String nickname;
    public String name;
    public String url;

    // TODO enable this constructor since JPA core is fixed
//    public BlogEntity(Long id, Long userId, String name, String url, Instant createdAt, Instant updatedAt) {
//        super(id, createdAt, updatedAt);
//        this.userId = userId;
//        this.name = name;
//        this.url = url;
//    }
}
