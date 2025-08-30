package nettee.series.driven.rdb.persistence.mapper;


import nettee.series.domain.Series;
import nettee.series.driven.rdb.entity.SeriesEntity;
import nettee.series.readmodel.SeriesQueryModels.SeriesDetail;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface SeriesEntityMapper {
    
    Series toDomain(SeriesEntity seriesEntity);
    SeriesDetail toSeriesDetail(SeriesEntity seriesEntity);
    SeriesEntity toEntity(Series series);
    
    default Optional<SeriesDetail> toOptionalSeriesDetail(SeriesEntity seriesEntity) {
        return Optional.ofNullable(toSeriesDetail(seriesEntity));
    }
}