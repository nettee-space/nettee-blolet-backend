package nettee.series.driven.rdb.persistence.mapper;


import nettee.series.domain.Series;
import nettee.series.driven.rdb.entity.SeriesEntity;
import nettee.series.readmodel.SeriesQueryModels.SeriesDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Base64;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface SeriesEntityMapper {
    
    @Mapping(target = "banner", source = "banner", qualifiedByName = "bytesToBase64")
    Series toDomain(SeriesEntity seriesEntity);
    
    @Mapping(target = "banner", source = "banner", qualifiedByName = "bytesToBase64")
    SeriesDetail toSeriesDetail(SeriesEntity seriesEntity);
    
    @Mapping(target = "banner", source = "banner", qualifiedByName = "base64ToBytes")
    SeriesEntity toEntity(Series series);
    
    default Optional<SeriesDetail> toOptionalSeriesDetail(SeriesEntity seriesEntity) {
        return Optional.ofNullable(toSeriesDetail(seriesEntity));
    }
    
    @Named("bytesToBase64")
    default String bytesToBase64(byte[] bytes) {
        return bytes != null ?
                Base64.getEncoder().withoutPadding().encodeToString(bytes) :
                null;
    }
    
    @Named("base64ToBytes")
    default byte[] base64ToBytes(String encoded) {
        return encoded != null ? Base64.getDecoder().decode(encoded) : null;
    }
}