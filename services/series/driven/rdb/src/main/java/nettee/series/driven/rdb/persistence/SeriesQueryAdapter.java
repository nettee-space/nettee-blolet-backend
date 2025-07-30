package nettee.series.driven.rdb.persistence;

import com.querydsl.core.types.Projections;
import nettee.series.application.port.SeriesQueryRepositoryPort;
import nettee.series.driven.rdb.entity.SeriesEntity;
import nettee.series.driven.rdb.persistence.mapper.SeriesEntityMapper;
import nettee.series.readmodel.SeriesQueryModels.SeriesSummary;
import nettee.series.readmodel.SeriesQueryModels.SeriesDetail;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static nettee.series.driven.rdb.entity.QSeriesEntity.seriesEntity;

@Repository
public class SeriesQueryAdapter extends QuerydslRepositorySupport implements SeriesQueryRepositoryPort {
    
    private final SeriesEntityMapper seriesEntityMapper;
    
    public SeriesQueryAdapter(final SeriesEntityMapper seriesEntityMapper) {
        super(SeriesEntity.class);
        this.seriesEntityMapper = seriesEntityMapper;
    }
    
    @Override
    public Optional<SeriesDetail> findBySeriesId(String seriesId) {
        return seriesEntityMapper.toOptionalSeriesDetail(
                getQuerydsl().createQuery()
                        .select(seriesEntity)
                        .from(seriesEntity)
                        .where(seriesEntity.id.eq(Long.valueOf(seriesId)))
                        .fetchOne()
        );
    }
    
    @Override
    public List<SeriesSummary> findAllByBlogId(String blogId) {
        return getQuerydsl().createQuery()
                .select(Projections.constructor(
                        SeriesSummary.class,
                        seriesEntity.id.stringValue(),
                        seriesEntity.blogId.stringValue(),
                        seriesEntity.title,
                        seriesEntity.displayOrder,
                        seriesEntity.createdAt,
                        seriesEntity.updatedAt
                ))
                .from(seriesEntity)
                .where(seriesEntity.blogId.eq(Long.valueOf(blogId)))
                .orderBy(seriesEntity.displayOrder.asc())
                .fetch();
    }
}
