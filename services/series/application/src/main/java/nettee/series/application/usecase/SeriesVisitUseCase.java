package nettee.series.application.usecase;

import nettee.series.readmodel.SeriesQueryModels.SeriesDetail;

public interface SeriesVisitUseCase {
    SeriesDetail visitSeries(String seriesId);
}
