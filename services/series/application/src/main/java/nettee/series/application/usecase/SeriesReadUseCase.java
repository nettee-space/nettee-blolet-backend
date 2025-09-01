package nettee.series.application.usecase;

import nettee.series.readmodel.SeriesQueryModels.SeriesDetail;
import nettee.series.readmodel.SeriesQueryModels.SeriesSummary;

import java.util.List;

public interface SeriesReadUseCase {
    
    SeriesDetail findDetailForOwner(String seriesId, String userId);

    List<SeriesSummary> getSeriesList(String blogId);
}
