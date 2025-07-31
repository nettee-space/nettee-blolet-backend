package nettee.article.driving.web;

import nettee.article.domain.Article;
import nettee.article.driving.web.dto.ArticleCommandDto.ArticleUpdateCommand;
import nettee.article.driving.web.dto.ArticleCommandDto.ArticleCreateCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleDtoMapper {
    Article toDomain(String blogId, ArticleCreateCommand articleCreateCommand);
    Article toDomain(String id, String blogId, ArticleUpdateCommand articleUpdateCommand);
}
