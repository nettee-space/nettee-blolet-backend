package nettee.series.article.application.service;

import lombok.RequiredArgsConstructor;
import nettee.blolet.article.domain.SeriesArticle;
import nettee.series.article.application.port.SeriesArticleCommandRepositoryPort;
import nettee.series.article.application.usecase.SeriesArticleCreateUseCase;
import nettee.series.article.application.usecase.SeriesArticleDeleteUseCase;
import nettee.series.article.application.usecase.SeriesArticleUpdateUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

import static nettee.blolet.article.exception.SeriesArticleErrorCode.SERIES_ARTICLE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SeriesArticleCommandService implements SeriesArticleCreateUseCase, SeriesArticleUpdateUseCase, SeriesArticleDeleteUseCase {
    
    private final SeriesArticleCommandRepositoryPort commandRepositoryPort;
    
    @Override
    public List<SeriesArticle> createSeriesArticleList(String seriesId, List<SeriesArticle> articleList) {
        assert seriesId != null;
        assert articleList != null;
        
        // 시리즈 ID 세팅
        articleList.forEach(article ->
                article.prepareUpdate()
                        .seriesId(seriesId)
                        .update()
        );
        
        return commandRepositoryPort.saveAll(articleList);
    }
    
    @Override
    public SeriesArticle updateDraftToArticle(String seriesId, String draftId, String articleId) {
        assert seriesId != null;
        assert draftId != null;
        assert articleId != null;
        
        var article = commandRepositoryPort.findBySeriesIdAndDraftId(seriesId, draftId)
                .orElseThrow(SERIES_ARTICLE_NOT_FOUND::exception);
        
        article.prepareUpdate()
                .articleId(articleId)
                .update();
        
        return commandRepositoryPort.updateDraftToArticle(article);
    }
    
    @Override
    public void deleteSeriesArticleList(String seriesId) {
        assert seriesId != null;
        
        commandRepositoryPort.deleteAllBySeriesId(seriesId);
    }
}
