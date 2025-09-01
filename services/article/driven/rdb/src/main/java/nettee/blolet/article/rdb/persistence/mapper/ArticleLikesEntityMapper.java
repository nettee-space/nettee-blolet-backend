package nettee.blolet.article.rdb.persistence.mapper;

import nettee.blolet.article.domain.ArticleLikes;
import nettee.blolet.article.rdb.entity.ArticleLikesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleLikesEntityMapper {
    ArticleLikesEntity toEntity(ArticleLikes domain);
    ArticleLikes toDomain(ArticleLikesEntity entity);
}
