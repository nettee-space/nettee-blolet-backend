package nettee.series.application.services;

import lombok.RequiredArgsConstructor;
import nettee.series.application.port.SeriesQueryRepositoryPort;
import nettee.series.application.usecase.SeriesReadUseCase;
import nettee.series.article.application.usecase.SeriesArticleReadUseCase;
import nettee.series.readmodel.SeriesQueryModels.SeriesDetail;
import nettee.series.readmodel.SeriesQueryModels.SeriesSummary;
import org.springframework.stereotype.Service;

import java.util.List;

import static nettee.series.exception.SeriesErrorCode.SERIES_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SeriesQueryService implements SeriesReadUseCase {
    
    private final SeriesQueryRepositoryPort queryRepositoryPort;
    private final SeriesArticleReadUseCase seriesArticleReadUseCase;
    
    @Override
    public SeriesDetail getSeries(String seriesId) {
        assert seriesId != null;
        
        var series = queryRepositoryPort.findBySeriesId(seriesId)
                .orElseThrow(SERIES_NOT_FOUND::exception);
        
        // 시리즈 게시물 조회
        var seriesArticleSummary = seriesArticleReadUseCase.getSeriesArticleList(seriesId);

        if(seriesArticleSummary != null && !seriesArticleSummary.isEmpty()) {
           return series.addSummaryList(seriesArticleSummary);
        }
        
        return series;
    }
    
    @Override
    public List<SeriesSummary> getSeriesList(String blogId) {
        assert blogId != null;
        
        return queryRepositoryPort.findAllByBlogId(blogId);
    }
}
