package nettee.article.driven.rdb.persistence.mapper;

import nettee.blolet.article.domain.ArticleLikes;
import nettee.article.driven.rdb.entity.ArticleLikesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleLikesEntityMapper {
    ArticleLikesEntity toEntity(ArticleLikes domain);
    ArticleLikes toDomain(ArticleLikesEntity entity);
}
