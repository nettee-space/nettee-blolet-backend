package nettee.series.article.application.service;

import lombok.RequiredArgsConstructor;
import nettee.series.article.application.port.SeriesArticleCommandRepositoryPort;
import nettee.series.article.application.usecase.SeriesArticleCreateUseCase;
import nettee.series.article.application.usecase.SeriesArticleDeleteUseCase;
import nettee.series.article.application.usecase.SeriesArticleUpdateUseCase;
import nettee.series.article.domain.SeriesArticle;
import org.springframework.stereotype.Service;

import java.util.List;

import static nettee.series.article.exception.SeriesArticleErrorCode.SERIES_ARTICLE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SeriesArticleCommandService implements SeriesArticleCreateUseCase, SeriesArticleUpdateUseCase, SeriesArticleDeleteUseCase {
    
    private final SeriesArticleCommandRepositoryPort commandRepositoryPort;
    
    @Override
    public List<SeriesArticle> createSeriesArticle(String seriesId, List<SeriesArticle> articleList) {
        assert seriesId != null;
        assert articleList != null;
        
        return articleList.stream()
                .map(article -> {
                    // 시리즈 아이디 생성
                    article.prepareUpdate()
                            .seriesId(seriesId)
                            .update();
                    
                    return commandRepositoryPort.save(article);
                })
                .toList();
    }

    @Override
    public SeriesArticle updateDraftToArticle(String seriesId, String draftId, String articleId) {
        var article = commandRepositoryPort.findByIdAndDraftId(seriesId, draftId)
                .orElseThrow(SERIES_ARTICLE_NOT_FOUND::exception);

        article.prepareUpdate()
                .seriesId(seriesId)
                .articleId(articleId)
                .update();

        return commandRepositoryPort.updateDraftToArticle(article);
    }
    
    @Override
    public void deleteSeriesArticle(String seriesId) {
        assert seriesId != null;
        
        commandRepositoryPort.delete(seriesId);
    }
}
