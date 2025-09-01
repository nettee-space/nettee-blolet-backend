package nettee.series.article.driven.rdb.persistence;

import nettee.blolet.article.readmodel.SeriesArticleQueryModels.SeriesArticleSummary;
import nettee.series.article.application.port.SeriesArticleQueryRepositoryPort;
import nettee.series.article.driven.rdb.entity.SeriesArticleEntity;
import nettee.series.article.driven.rdb.persistence.mapper.SeriesArticleEntityMapper;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static nettee.series.article.driven.rdb.entity.QSeriesArticleEntity.seriesArticleEntity;

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
