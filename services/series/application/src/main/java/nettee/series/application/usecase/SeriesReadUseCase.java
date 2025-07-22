package nettee.series.application.usecase;

import nettee.series.readmodel.SeriesQueryModels.SeriesDetail;
import nettee.series.readmodel.SeriesQueryModels.SeriesSummary;

import java.util.List;

public interface SeriesReadUseCase {
    
    SeriesDetail getSeries(String seriesId);
    
    List<SeriesSummary> getSeriesList(String blogId);
}
