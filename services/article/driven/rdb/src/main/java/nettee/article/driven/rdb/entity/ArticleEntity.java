package nettee.article.driven.rdb.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import nettee.article.driven.rdb.entity.type.builder.ArticleEntityStatus;
import nettee.article.driven.rdb.entity.type.builder.ArticleEntityStatusConverter;
import nettee.jpa.support.SnowflakeBaseTimeEntity;

import java.util.Objects;

@Entity
@Table(schema = "article", name = "article")
public class ArticleEntity extends SnowflakeBaseTimeEntity {

    public Long blogId;
    public Long entryBlockId;

    public String title;
    public String content;
    public String path;

    public Integer totalViews = 0;
    public Integer totalLikes = 0;
    public Integer totalShares = 0;

    @Convert(converter = ArticleEntityStatusConverter.class)
    public ArticleEntityStatus status;

    @Builder(
            builderClassName = "updateArticleEntityBuilder",
            builderMethodName = "prepareArticleEntityUpdate",
            buildMethodName = "update"
    )
    public void update(String title, String content) {
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(content, "Content cannot be null");

        this.title = title;
        this.content = content;
    }
}
