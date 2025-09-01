package nettee.blolet.article.rdb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nettee.jpa.support.SnowflakeBaseTimeEntity;

import java.util.Objects;

@Entity
@Table(name = "series_article", schema = "article")
@SuperBuilder
@NoArgsConstructor
public class SeriesArticleEntity extends SnowflakeBaseTimeEntity {
    
    public Long seriesId;
    
    public Long articleId;
    
    public Long draftId;
    
    public Integer displayOrder;
    
    @Builder(
            builderClassName = "UpdateSeriesArticleEntityBuilder",
            builderMethodName = "prepareUpdate",
            buildMethodName = "updateArticleId"
    )
    public void updateArticleId(Long articleId) {
        Objects.requireNonNull(articleId, "ArticleId cannot be null");
        
        this.articleId = articleId;
    }
}
