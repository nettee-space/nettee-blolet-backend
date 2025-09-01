package nettee.series.article.application.service;

import lombok.RequiredArgsConstructor;
import nettee.blolet.article.readmodel.SeriesArticleQueryModels.SeriesArticleSummary;
import nettee.series.article.application.port.SeriesArticleQueryRepositoryPort;
import nettee.series.article.application.usecase.SeriesArticleReadUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesArticleQueryService implements SeriesArticleReadUseCase {
    
    private final SeriesArticleQueryRepositoryPort queryRepositoryPort;
    
    @Override
    public List<SeriesArticleSummary> getSeriesArticleList(String seriesId) {
        assert seriesId != null;
        
        return queryRepositoryPort.findBySeriesId(seriesId);
    }
}
