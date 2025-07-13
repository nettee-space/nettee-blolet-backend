package nettee.series.article.application.service;

import lombok.RequiredArgsConstructor;
import nettee.series.article.application.port.SeriesArticleCommandRepositoryPort;
import nettee.series.article.application.usecase.SeriesArticleCreateUseCase;
import nettee.series.article.application.usecase.SeriesArticleDeleteUseCase;
import nettee.series.article.application.usecase.SeriesArticleUpdateUseCase;
import nettee.series.article.domain.SeriesArticle;
import nettee.series.article.readmodel.SeriesArticleQueryModels.SeriesArticleSummary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesArticleCommandService implements SeriesArticleCreateUseCase, SeriesArticleUpdateUseCase, SeriesArticleDeleteUseCase {
 
    private final SeriesArticleCommandRepositoryPort commandRepositoryPort;
    
    @Override
    public List<SeriesArticleSummary> createSeriesArticle(SeriesArticle article) {
        
        
        return null;
    }
    
    @Override
    public List<SeriesArticleSummary> updateSeriesArticle(SeriesArticle article) {
        return null;
    }
    
    @Override
    public void deleteSeriesArticle(String seriesId) {
        commandRepositoryPort.delete(seriesId);
    }
}
