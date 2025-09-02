package nettee.blolet.article.rdb.persistence;

import nettee.blolet.article.application.port.SeriesArticleQueryRepositoryPort;
import nettee.blolet.article.rdb.entity.SeriesArticleEntity;
import nettee.blolet.article.rdb.mapper.SeriesArticleEntityMapper;
import nettee.blolet.article.readmodel.SeriesArticleQueryModels.SeriesArticleSummary;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static nettee.blolet.article.rdb.entity.QSeriesArticleEntity.seriesArticleEntity;

@Repository
public class SeriesArticleQueryAdapter extends QuerydslRepositorySupport implements SeriesArticleQueryRepositoryPort {

    private final SeriesArticleEntityMapper seriesArticleEntityMapper;

    public SeriesArticleQueryAdapter(final SeriesArticleEntityMapper seriesArticleEntityMapper) {
        super(SeriesArticleEntity.class);
        this.seriesArticleEntityMapper = seriesArticleEntityMapper;
    }

    @Override
    public List<SeriesArticleSummary> findBySeriesId(String seriesId) {
        var articleList = getQuerydsl().createQuery()
                .select(seriesArticleEntity)
                .from(seriesArticleEntity)
                .where(seriesArticleEntity.seriesId.eq(Long.valueOf(seriesId)))
                .fetch();

        return articleList.stream()
                .map(seriesArticleEntityMapper::toSeriesArticleSummary)
                .toList();
    }
}
