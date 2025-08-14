package nettee.article.driven.rdb.persistence.mapper;

import nettee.article.domain.Article;
import nettee.article.driven.rdb.entity.ArticleEntity;
import nettee.article.driven.rdb.projection.ArticleQueryProjections.ArticleDetailProjection;
import nettee.article.driven.rdb.projection.ArticleQueryProjections.ArticleSummaryProjection;
import nettee.article.readmodel.ArticleQueryModels.ArticleDetail;
import nettee.article.readmodel.ArticleQueryModels.ArticleSummary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleEntityMapper {

    Article toDomain(ArticleEntity articleEntity);
    ArticleEntity toEntity(Article article);
    ArticleDetail toSummary(ArticleDetailProjection articleDetailProjection);
    ArticleSummary toSummary(ArticleSummaryProjection articleSummaryProjection);
}
