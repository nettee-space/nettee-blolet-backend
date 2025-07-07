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
    
    private Long seriesId;
    
    private Long articleId;
    
    private Long draftId;
    
    private Integer displayOrder;
    
    private Instant createdAt;
    
    private Instant updatedAt;
    
    @Builder(
            builderClassName = "updateSeriesBuilder",
            builderMethodName = "prepareUpdate",
            buildMethodName = "update"
    )
    public void update(Integer displayOrder) {
        Objects.requireNonNull(displayOrder, "DisplayOrder cannot be null");
        
        this.displayOrder = displayOrder;
        this.updatedAt = Instant.now();
    }
}
