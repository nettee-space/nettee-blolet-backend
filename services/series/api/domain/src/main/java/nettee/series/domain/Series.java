package nettee.series.domain;

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
public class Series {
    
    private Long id;
    
    private Long blogId;
    
    private String title;
    
    private Integer displayOrder;
    
    private Instant createdAt;
    
    private Instant updatedAt;
    
    @Builder(
            builderClassName = "updateSeriesBuilder",
            builderMethodName = "prepareUpdate",
            buildMethodName = "update"
    )
    public void update(String title, Integer displayOrder) {
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(displayOrder, "DisplayOrder cannot be null");
        
        this.title = title;
        this.displayOrder = displayOrder;
        this.updatedAt = Instant.now();
    }
}
