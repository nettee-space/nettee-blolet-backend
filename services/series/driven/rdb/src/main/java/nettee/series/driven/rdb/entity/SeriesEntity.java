package nettee.series.driven.rdb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nettee.jpa.support.LongBaseTimeEntity;

import java.util.Objects;

@Entity
@Table(name = "series", schema = "series")
@SuperBuilder
@NoArgsConstructor
public class SeriesEntity extends LongBaseTimeEntity {
    
    @Column(nullable = false)
    public Long blogId;
    
    public String title;
    
    public String description;
    
    public String bannerUrl;
    
    public Integer displayOrder;
    
    @Builder(
            builderClassName = "UpdateSeriesEntityBuilder",
            builderMethodName = "prepareUpdate",
            buildMethodName = "update"
    )
    public void update(String title, String description, String banner, Integer displayOrder) {
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(displayOrder, "DisplayOrder cannot be null");
        
        this.title = title;
        this.description = description;
        this.bannerUrl = banner;
        this.displayOrder = displayOrder;
    }
}
