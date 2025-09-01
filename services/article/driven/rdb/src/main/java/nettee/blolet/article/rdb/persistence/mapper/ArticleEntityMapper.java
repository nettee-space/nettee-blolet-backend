package nettee.blolet.article.rdb.persistence.mapper;

import nettee.blolet.article.domain.Article;
import nettee.blolet.article.rdb.entity.ArticleEntity;
import nettee.blolet.article.rdb.projection.ArticleQueryProjections.ArticleDetailProjection;
import nettee.blolet.article.rdb.projection.ArticleQueryProjections.ArticleSummaryProjection;
import nettee.blolet.article.readmodel.ArticleQueryModels.ArticleDetail;
import nettee.blolet.article.readmodel.ArticleQueryModels.ArticleSummary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleEntityMapper {

    Article toDomain(ArticleEntity articleEntity);
    ArticleEntity toEntity(Article article);
    ArticleDetail toSummary(ArticleDetailProjection articleDetailProjection);
    ArticleSummary toSummary(ArticleSummaryProjection articleSummaryProjection);
}
