package nettee.series.driving.web.mapper;

import nettee.series.domain.Series;
import nettee.series.driving.web.dto.SeriesCommandDto.SeriesCreateCommand;
import nettee.series.driving.web.dto.SeriesCommandDto.SeriesUpdateCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeriesDtoMapper {
    
    Series toDomain(SeriesCreateCommand command, String blogId);
    
    Series toDomain(SeriesUpdateCommand command);
}
