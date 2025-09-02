package nettee.blolet.article.web.mapper;

import nettee.blolet.article.domain.SeriesArticle;
import nettee.blolet.article.web.dto.SeriesArticleCommandDto.SeriesArticleCreateCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeriesArticleDtoMapper {
    
    SeriesArticle toDomain(SeriesArticleCreateCommand command);
}
