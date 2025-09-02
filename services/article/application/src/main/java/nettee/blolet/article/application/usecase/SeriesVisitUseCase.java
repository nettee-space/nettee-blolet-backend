package nettee.blolet.article.application.usecase;

import nettee.blolet.article.readmodel.SeriesQueryModels.SeriesDetail;

public interface SeriesVisitUseCase {
    SeriesDetail findDetailForPublic(String seriesId);
}
