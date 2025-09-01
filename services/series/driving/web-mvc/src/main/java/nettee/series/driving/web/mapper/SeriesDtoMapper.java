package nettee.series.driving.web.mapper;

import nettee.blolet.article.domain.Series;
import nettee.series.driving.web.dto.SeriesCommandDto.SeriesCreateCommand;
import nettee.series.driving.web.dto.SeriesCommandDto.SeriesUpdateCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeriesDtoMapper {
    
    Series toDomain(SeriesCreateCommand command);
    
    Series toDomain(SeriesUpdateCommand command, String seriesId);
}
