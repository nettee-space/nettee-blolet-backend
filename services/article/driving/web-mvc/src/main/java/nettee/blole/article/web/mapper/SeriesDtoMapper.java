package nettee.blole.article.web.mapper;

import nettee.blole.article.web.dto.SeriesCommandDto.SeriesCreateCommand;
import nettee.blole.article.web.dto.SeriesCommandDto.SeriesUpdateCommand;
import nettee.blolet.article.domain.Series;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeriesDtoMapper {
    
    Series toDomain(SeriesCreateCommand command);
    
    Series toDomain(SeriesUpdateCommand command, String seriesId);
}
