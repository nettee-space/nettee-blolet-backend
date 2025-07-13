package nettee.series.application.port;

import nettee.series.domain.Series;
import nettee.series.readmodel.SeriesQueryModels.SeriesSummary;
import nettee.series.readmodel.SeriesQueryModels.SeriesDetail;

import java.util.List;
import java.util.Optional;

public interface SeriesQueryRepositoryPort {
    
    Optional<SeriesDetail> findByBlogIdAndSeriesId(String blogId, String seriesId);
    
    List<SeriesSummary> findAllByBlogId(String blogId);
}
