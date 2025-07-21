package nettee.series.article.application.service;

import lombok.RequiredArgsConstructor;
import nettee.series.article.application.port.SeriesArticleCommandRepositoryPort;
import nettee.series.article.application.usecase.SeriesArticleCreateUseCase;
import nettee.series.article.application.usecase.SeriesArticleDeleteUseCase;
import nettee.series.article.domain.SeriesArticle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesArticleCommandService implements SeriesArticleCreateUseCase, SeriesArticleDeleteUseCase {
    
    private final SeriesArticleCommandRepositoryPort commandRepositoryPort;
    
    @Override
    public List<SeriesArticle> createSeriesArticle(String seriesId, List<SeriesArticle> articleList) {
        assert seriesId != null;
        assert articleList != null;
        
        return articleList.stream()
                .map(article -> {
                    // 시리즈 아이디 생성
                    article.update(seriesId);
                    
                    return commandRepositoryPort.save(article);
                })
                .toList();
    }
    
    @Override
    public void deleteSeriesArticle(String seriesId) {
        assert seriesId != null;
        
        commandRepositoryPort.delete(seriesId);
    }
}
