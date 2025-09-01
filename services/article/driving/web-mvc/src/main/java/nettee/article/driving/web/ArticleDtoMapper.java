package nettee.article.driving.web;

import nettee.blolet.article.domain.Article;
import nettee.blolet.article.domain.ArticleLikes;
import nettee.article.driving.web.dto.ArticleCommandDto.ArticleUpdateCommand;
import nettee.article.driving.web.dto.ArticleCommandDto.ArticleCreateCommand;
import nettee.article.driving.web.dto.ArticleLikesCommandDto.ArticleLikesCreateCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleDtoMapper {

    Article toDomain(ArticleCreateCommand articleCreateCommand);
    Article toDomain(String id, ArticleUpdateCommand articleUpdateCommand);

    ArticleLikes toLikesDomain(String articleId, String userId, ArticleLikesCreateCommand articleLikesCreateCommand);
}
