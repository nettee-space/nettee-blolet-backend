package nettee.blolet.article.rdb.mapper;

import nettee.blolet.article.domain.SeriesArticle;
import nettee.blolet.article.rdb.entity.SeriesArticleEntity;
import nettee.blolet.article.readmodel.SeriesArticleQueryModels.SeriesArticleSummary;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface SeriesArticleEntityMapper {
    
    SeriesArticle toDomain(SeriesArticleEntity seriesArticleEntity);
    
    SeriesArticleSummary toSeriesArticleSummary(SeriesArticleEntity seriesArticleEntity);
    
    SeriesArticleEntity toEntity(SeriesArticle seriesArticles);
    
    default Optional<SeriesArticle> toOptionalSeriesArticle(SeriesArticleEntity seriesArticleEntity) {
        return Optional.ofNullable(toDomain(seriesArticleEntity));
    }
}