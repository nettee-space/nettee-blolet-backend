package nettee.series.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.series.article.domain.SeriesArticle;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Series {
    
    private String id;
    
    private String blogId;
    
    private String title;
    
    private String description;
    
    private String banner;
    
    private Integer displayOrder;
    
    private List<SeriesArticle> seriesArticleList;
    
    private Instant createdAt;
    
    private Instant updatedAt;
    
    @Builder(
            builderClassName = "updateSeriesBuilder",
            builderMethodName = "prepareUpdate",
            buildMethodName = "update"
    )
    public void update(String title, Integer displayOrder, String description, String banner, List<SeriesArticle> seriesArticleList) {
        if (title != null) this.title = title;
        
        if (displayOrder != null) this.displayOrder = displayOrder;
        
        if (description != null) this.description = description;
        
        if (banner != null) this.banner = banner;
        
        if (seriesArticleList != null) this.seriesArticleList = seriesArticleList;
        
        this.updatedAt = Instant.now();
    }
}
