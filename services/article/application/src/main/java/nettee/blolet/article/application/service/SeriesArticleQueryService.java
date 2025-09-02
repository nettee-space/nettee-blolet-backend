package nettee.blolet.article.application.service;

import lombok.RequiredArgsConstructor;
import nettee.blolet.article.application.port.SeriesArticleQueryRepositoryPort;
import nettee.blolet.article.application.usecase.SeriesArticleReadUseCase;
import nettee.blolet.article.readmodel.SeriesArticleQueryModels.SeriesArticleSummary;
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
