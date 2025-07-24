package nettee.series.driven.rdb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;
import nettee.jpa.support.LongBaseTimeEntity;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Objects;

@DynamicUpdate
@Entity(name = "series")
public class SeriesEntity extends LongBaseTimeEntity {
    
    @Column(nullable = false)
    public Long blogId;
    
    public String title;
    
    public String description;
    
    public byte[] banner;
    
    public Integer displayOrder;
    
    @Builder(
            builderClassName = "updateSeriesEntityBuilder",
            builderMethodName = "prepareSeriesEntityUpdate",
            buildMethodName = "update"
    )
    public void update(String title, String description, byte[] banner, Integer displayOrder) {
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(displayOrder, "DisplayOrder cannot be null");
        
        this.title = title;
        this.description = description;
        this.banner = banner;
        this.displayOrder = displayOrder;
    }
}
