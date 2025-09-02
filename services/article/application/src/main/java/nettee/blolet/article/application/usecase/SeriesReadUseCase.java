package nettee.blolet.article.application.usecase;

import nettee.blolet.article.readmodel.SeriesQueryModels.SeriesDetail;
import nettee.blolet.article.readmodel.SeriesQueryModels.SeriesSummary;

import java.util.List;

public interface SeriesReadUseCase {
    
    SeriesDetail findDetailForOwner(String seriesId, String userId);

    List<SeriesSummary> getSeriesList(String blogId);
}
