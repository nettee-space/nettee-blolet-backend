package nettee.series.application.port;

import nettee.series.readmodel.SeriesQueryModels.SeriesDetail;
import nettee.series.readmodel.SeriesQueryModels.SeriesSummary;

import java.util.List;
import java.util.Optional;

public interface SeriesQueryRepositoryPort {
    
    
    Optional<SeriesDetail> findBySeriesIdAndOwnership(String seriesId, String userBlogId);
    List<SeriesSummary> findAllByBlogId(String blogId);
}
