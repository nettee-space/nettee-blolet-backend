package nettee.series.article.driven.rdb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import nettee.jpa.support.LongBaseTimeEntity;

import java.util.Objects;

@Entity
@Table(name = "series_article", schema = "series")
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
