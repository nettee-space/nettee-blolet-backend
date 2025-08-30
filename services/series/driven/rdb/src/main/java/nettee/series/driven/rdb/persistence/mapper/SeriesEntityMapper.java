package nettee.series.driven.rdb.persistence.mapper;


import nettee.series.article.readmodel.SeriesArticleQueryModels.SeriesArticleSummary;
import nettee.series.domain.Series;
import nettee.series.driven.rdb.entity.SeriesEntity;
import nettee.series.driven.rdb.persistence.projection.SeriesProjections.SeriesArticleSummaryProjection;
import nettee.series.driven.rdb.persistence.projection.SeriesProjections.SeriesDetailProjection;
import nettee.series.readmodel.SeriesQueryModels.SeriesDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeriesEntityMapper {
    
    Series toDomain(SeriesEntity seriesEntity);
    SeriesEntity toEntity(Series series);

    SeriesDetail toDetail(SeriesDetailProjection projection, List<SeriesArticleSummaryProjection> articles);

    /**
     * NOTE will be used by `SeriesDetail toSeriesDetail(...)` or other functions.
     *
     * @param projection 시리즈 아티클 요약(summary) 조회 아이템
     * @return read model
     */
    @Mapping(target = "title", source = "currentTitle")
    SeriesArticleSummary toSeriesArticleSummary(SeriesArticleSummaryProjection projection);
}