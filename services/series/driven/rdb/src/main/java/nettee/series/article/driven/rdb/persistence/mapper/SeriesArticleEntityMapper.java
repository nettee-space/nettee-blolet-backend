package nettee.series.article.driven.rdb.persistence.mapper;

import nettee.blolet.article.domain.SeriesArticle;
import nettee.blolet.article.readmodel.SeriesArticleQueryModels.SeriesArticleSummary;
import nettee.series.article.driven.rdb.entity.SeriesArticleEntity;
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