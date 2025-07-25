package nettee.series.article.driven.rdb.entity;

import jakarta.persistence.Entity;
import lombok.Builder;
import nettee.jpa.support.LongBaseTimeEntity;

import java.util.Objects;

@Entity(name = "series_article")
public class SeriesArticleEntity extends LongBaseTimeEntity {
    
    public Long seriesId;
    
    public Long articleId;
    
    public Long draftId;
    
    public Integer displayOrder;
    
    @Builder(
            builderClassName = "updateSeriesArticleEntityBuilder",
            builderMethodName = "prepareUpdate",
            buildMethodName = "updateArticleId"
    )
    public void updateArticleId(Long articleId) {
        Objects.requireNonNull(articleId, "ArticleId cannot be null");
        
        this.articleId = articleId;
    }
}
