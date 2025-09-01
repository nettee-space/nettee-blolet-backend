package nettee.blole.article.web.mapper;

import nettee.blole.article.web.dto.SeriesArticleCommandDto.SeriesArticleCreateCommand;
import nettee.blolet.article.domain.SeriesArticle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeriesArticleDtoMapper {
    
    SeriesArticle toDomain(SeriesArticleCreateCommand command);
}
