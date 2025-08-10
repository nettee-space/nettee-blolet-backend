package nettee.blolet.blog.rdb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nettee.jpa.support.SnowflakeBaseTimeEntity;

@Getter
@SuperBuilder
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
    public String name;
    public String url;
}
