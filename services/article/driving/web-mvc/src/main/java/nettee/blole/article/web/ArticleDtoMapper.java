package nettee.blole.article.web;

import nettee.blole.article.web.dto.ArticleCommandDto.ArticleCreateCommand;
import nettee.blole.article.web.dto.ArticleCommandDto.ArticleUpdateCommand;
import nettee.blole.article.web.dto.ArticleLikesCommandDto.ArticleLikesCreateCommand;
import nettee.blolet.article.domain.Article;
import nettee.blolet.article.domain.ArticleLikes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleDtoMapper {

    Article toDomain(ArticleCreateCommand articleCreateCommand);
    Article toDomain(String id, ArticleUpdateCommand articleUpdateCommand);

    ArticleLikes toLikesDomain(String articleId, String userId, ArticleLikesCreateCommand articleLikesCreateCommand);
}
