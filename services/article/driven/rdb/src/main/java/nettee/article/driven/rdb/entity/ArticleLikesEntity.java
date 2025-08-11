package nettee.article.driven.rdb.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nettee.article.domain.ArticleLikesStatus;
import nettee.article.driven.rdb.entity.type.builder.ArticleLikesEntityStatusConverter;
import nettee.jpa.support.SnowflakeBaseTimeEntity;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "article", name = "article_likes")
public class ArticleLikesEntity extends SnowflakeBaseTimeEntity {

    private String userId;

    private String profileId;

    private String articleId;

    private Integer count;

    @Convert(converter = ArticleLikesEntityStatusConverter.class)
    private ArticleLikesStatus status;
}
