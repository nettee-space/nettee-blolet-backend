package nettee.series.application.usecase;

import nettee.series.domain.Series;

import java.util.List;

public interface SeriesReadUseCase {
    
    Series getSeries(String blogId, String seriesId);
    
    List<Series> getSeriesLiST(String blogId);
}
