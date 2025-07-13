package nettee.series.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.series.article.domain.SeriesArticle;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Series {
    
    private String id;
    
    private String blogId;
    
    private String title;
    
    private String description;
    
    private byte[] banner;
    
    private Integer displayOrder;
    
    private List<SeriesArticle> seriesArticleList;
    
    private Instant createdAt;
    
    private Instant updatedAt;
    
    @Builder(
            builderClassName = "updateSeriesBuilder",
            builderMethodName = "prepareUpdate",
            buildMethodName = "update"
    )
    public void update(String title, Integer displayOrder, String description, byte[] banner) {
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(displayOrder, "DisplayOrder cannot be null");
        
        this.title = title;
        this.description = description;
        this.banner = banner;
        this.displayOrder = displayOrder;
        this.updatedAt = Instant.now();
    }
}
