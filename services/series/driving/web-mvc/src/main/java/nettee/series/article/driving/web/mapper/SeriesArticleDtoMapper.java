package nettee.series.article.driving.web.mapper;

import nettee.blolet.article.domain.SeriesArticle;
import nettee.series.article.driving.web.dto.SeriesArticleCommandDto.SeriesArticleCreateCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeriesArticleDtoMapper {
    
    SeriesArticle toDomain(SeriesArticleCreateCommand command);
}
