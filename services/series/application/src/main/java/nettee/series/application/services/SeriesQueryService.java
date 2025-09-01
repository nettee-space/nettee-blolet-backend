package nettee.series.application.services;

import lombok.RequiredArgsConstructor;
import nettee.series.application.port.SeriesQueryRepositoryPort;
import nettee.series.application.usecase.SeriesReadUseCase;
import nettee.series.application.usecase.SeriesVisitUseCase;
import nettee.series.readmodel.SeriesQueryModels.SeriesDetail;
import nettee.series.readmodel.SeriesQueryModels.SeriesSummary;
import org.springframework.stereotype.Service;

import java.util.List;

import static nettee.series.exception.SeriesErrorCode.SERIES_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SeriesQueryService implements SeriesReadUseCase, SeriesVisitUseCase {
    
    private final SeriesQueryRepositoryPort queryRepositoryPort;
    
    @Override
    public SeriesDetail getSeries(String seriesId) {
        assert seriesId != null;

        return queryRepositoryPort.findBySeriesId(seriesId)
                .orElseThrow(SERIES_NOT_FOUND::exception);
    }
    
    @Override
    public List<SeriesSummary> getSeriesList(String blogId) {
        assert blogId != null;
        
        return queryRepositoryPort.findAllByBlogId(blogId);
    }

    @Override
    public SeriesDetail visitSeries(String seriesId) {
        return queryRepositoryPort.findExceptDraftsBySeriesId(seriesId)
                .orElseThrow(SERIES_NOT_FOUND::exception);
    }
}
