package nettee.series.application.port;

import nettee.series.readmodel.SeriesQueryModels.SeriesDetail;
import nettee.series.readmodel.SeriesQueryModels.SeriesSummary;

import java.util.List;
import java.util.Optional;

public interface SeriesQueryRepositoryPort {
    
    Optional<SeriesDetail> findBySeriesId(String seriesId);
    
    List<SeriesSummary> findAllByBlogId(String blogId);
}
