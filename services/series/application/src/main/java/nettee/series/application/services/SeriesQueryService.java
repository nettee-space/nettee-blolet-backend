package nettee.series.application.services;

import lombok.RequiredArgsConstructor;
import nettee.series.application.port.SeriesQueryRepositoryPort;
import nettee.series.application.usecase.SeriesReadUseCase;
import nettee.series.readmodel.SeriesQueryModels.SeriesDetail;
import nettee.series.readmodel.SeriesQueryModels.SeriesSummary;
import org.springframework.stereotype.Service;

import java.util.List;

import static nettee.series.exception.SeriesErrorCode.SERIES_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SeriesQueryService implements SeriesReadUseCase {
    
    private final SeriesQueryRepositoryPort queryRepositoryPort;
    
    @Override
    public SeriesDetail getSeries(String blogId, String seriesId) {
        assert blogId != null;
        assert seriesId != null;
        
        SeriesDetail series = queryRepositoryPort.findByBlogIdAndSeriesId(blogId,seriesId)
                .orElseThrow(SERIES_NOT_FOUND::exception);
        
        // TODO 시리즈 게시물 아티클 조회 후 SeriesDetail 삽입
        
        return series;
    }
    
    @Override
    public List<SeriesSummary> getSeriesList(String blogId) {
        assert blogId != null;
        
        return queryRepositoryPort.findAllByBlogId(blogId);
    }
}
