package nettee.blolet.article.application.port;

import nettee.blolet.article.readmodel.SeriesQueryModels.SeriesDetail;
import nettee.blolet.article.readmodel.SeriesQueryModels.SeriesSummary;

import java.util.List;
import java.util.Optional;

public interface SeriesQueryRepositoryPort {
    
    Optional<SeriesDetail> findByIdAndOwnership(String seriesId, String userBlogId);
    Optional<SeriesDetail> findExceptDraftsById(String seriesId);

    List<SeriesSummary> findAllByBlogId(String blogId);
}
