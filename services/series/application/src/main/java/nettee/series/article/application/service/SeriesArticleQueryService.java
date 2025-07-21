package nettee.series.article.application.service;

import lombok.RequiredArgsConstructor;
import nettee.series.article.application.port.SeriesArticleQueryRepositoryPort;
import nettee.series.article.application.usecase.SeriesArticleReadUseCase;
import nettee.series.article.domain.SeriesArticle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesArticleQueryService implements SeriesArticleReadUseCase {
    
    private final SeriesArticleQueryRepositoryPort queryRepositoryPort;
    
    @Override
    public List<SeriesArticle> getSeriesArticleList(String seriesId) {
        assert seriesId != null;
        
        return queryRepositoryPort.findBySeriesId(seriesId);
    }
}
