package nettee.series.article.driven.rdb.persistence;

import lombok.RequiredArgsConstructor;
import nettee.blolet.article.domain.SeriesArticle;
import nettee.blolet.article.exception.SeriesArticleException;
import nettee.series.article.application.port.SeriesArticleCommandRepositoryPort;
import nettee.series.article.driven.rdb.entity.SeriesArticleEntity;
import nettee.series.article.driven.rdb.persistence.mapper.SeriesArticleEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static nettee.blolet.article.exception.SeriesArticleErrorCode.SERIES_ARTICLE_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class SeriesArticleCommandAdapter implements SeriesArticleCommandRepositoryPort {
    
    private final SeriesArticleJpaRepository seriesArticleJpaRepository;
    private final SeriesArticleEntityMapper seriesArticleEntityMapper;
    
    @Override
    public Optional<SeriesArticle> findBySeriesIdAndDraftId(String seriesId, String draftId) {
        return seriesArticleEntityMapper.toOptionalSeriesArticle(
                seriesArticleJpaRepository.findBySeriesIdAndDraftId(Long.valueOf(seriesId), Long.valueOf(draftId))
        );
    }
    
    @Override
    public List<SeriesArticle> saveAll(List<SeriesArticle> article) {
        List<SeriesArticleEntity> newArticles = seriesArticleJpaRepository.saveAll(article.stream()
                .map(seriesArticleEntityMapper::toEntity)
                .toList());
        
        return newArticles.stream()
                .map(seriesArticleEntityMapper::toDomain)
                .toList();
    }
    
    @Override
    public SeriesArticle updateDraftToArticle(SeriesArticle article) {
        var existsArticle = seriesArticleJpaRepository.findBySeriesIdAndDraftId
                (Long.valueOf(article.getSeriesId()), Long.valueOf(article.getDraftId()));
        
        if (existsArticle == null) {
            throw new SeriesArticleException(SERIES_ARTICLE_NOT_FOUND);
        }
        
        existsArticle.prepareUpdate()
                .articleId(Long.valueOf(article.getArticleId()))
                .updateArticleId();
        
        return seriesArticleEntityMapper.toDomain(existsArticle);
    }
    
    @Override
    public void deleteAllBySeriesId(String seriesId) {
        seriesArticleJpaRepository.deleteAllBySeriesId(Long.valueOf(seriesId));
    }
}
