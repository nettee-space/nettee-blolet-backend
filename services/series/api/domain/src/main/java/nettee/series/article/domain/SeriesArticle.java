package nettee.series.article.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Objects;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeriesArticle {
    
    private String seriesId;
    
    private String articleId;
    
    private String draftId;
    
    private Integer displayOrder;
    
    private Instant createdAt;
    
    private Instant updatedAt;
    
    @Builder(
            builderClassName = "updateSeriesBuilder",
            builderMethodName = "prepareUpdate",
            buildMethodName = "update"
    )
    public void update(String seriesId) {
        Objects.requireNonNull(seriesId, "seriesId cannot be null");
        
        this.seriesId = seriesId;
        this.updatedAt = Instant.now();
    }
}
