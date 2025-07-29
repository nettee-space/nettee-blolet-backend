package nettee.article.driven.rdb.persistence.mapper;

import nettee.article.domain.Article;
import nettee.article.driven.rdb.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleEntityMapper {
    Article toDomain(ArticleEntity articleEntity);
    ArticleEntity toEntity(Article article);
}
