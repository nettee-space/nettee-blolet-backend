package nettee.blolet.article.rdb.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nettee.blolet.article.rdb.entity.type.builder.ArticleLikesEntityStatus;
import nettee.blolet.article.rdb.entity.type.builder.ArticleLikesEntityStatusConverter;
import nettee.jpa.support.SnowflakeBaseTimeEntity;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "article", name = "article_likes")
public class ArticleLikesEntity extends SnowflakeBaseTimeEntity {

    private Long userId;

    private Long profileId;

    private Long articleId;

    private Integer count;

    @Convert(converter = ArticleLikesEntityStatusConverter.class)
    private ArticleLikesEntityStatus status;
}
