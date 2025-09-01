package nettee.series.driven.rdb.persistence;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import nettee.series.application.port.SeriesQueryRepositoryPort;
import nettee.series.driven.rdb.entity.SeriesEntity;
import nettee.series.driven.rdb.persistence.mapper.SeriesEntityMapper;
import nettee.series.driven.rdb.persistence.projection.SeriesProjections.SeriesArticleSummaryProjection;
import nettee.series.driven.rdb.persistence.projection.SeriesProjections.SeriesDetailProjection;
import nettee.series.readmodel.SeriesQueryModels.SeriesDetail;
import nettee.series.readmodel.SeriesQueryModels.SeriesSummary;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static nettee.blolet.article.rdb.entity.QArticleEntity.articleEntity;
import static nettee.blolet.article.rdb.entity.QDraftEntity.draftEntity;
import static nettee.series.article.driven.rdb.entity.QSeriesArticleEntity.seriesArticleEntity;
import static nettee.series.driven.rdb.entity.QSeriesEntity.seriesEntity;
import static nettee.series.exception.SeriesErrorCode.SERIES_NOT_FOUND;

@Repository
public class SeriesQueryAdapter extends QuerydslRepositorySupport implements SeriesQueryRepositoryPort {
    
    private final SeriesEntityMapper mapper;
    
    public SeriesQueryAdapter(final SeriesEntityMapper seriesEntityMapper) {
        super(SeriesEntity.class);
        this.mapper = seriesEntityMapper;
    }

    /**
     * 트래픽 절감, 안정적인 성능, 높은 유지보수성을 위하여 '싱글쿼리(단일 쿼리)' 대신 '스플릿 쿼리(분할 쿼리)'를 사용합니다.
     *
     * @param seriesId 시리즈 아이디
     * @param userBlogId 로그인 사용자의 블로그 아이디(지금은 하나). 지금은 블로그 아이디로 소유권을 확인 중.
     * @return Optional series detail model
     */
    @Override
    public Optional<SeriesDetail> findByIdAndOwnership(String seriesId, String userBlogId) {
        long longSeriesId = Long.parseLong(seriesId);
        long longOwnerBlogId = Long.parseLong(userBlogId);

        SeriesDetailProjection seriesDetail = querySeriesDetail(longSeriesId);
        if (seriesDetail == null) throw SERIES_NOT_FOUND.exception();
        boolean isOwner = seriesDetail.blogId() == longOwnerBlogId;

        var query = getQuerydsl().createQuery()
                .select(Projections.constructor(
                        SeriesArticleSummaryProjection.class,
//                        seriesArticleEntity.seriesId,
                        seriesArticleEntity.articleId,
                        seriesArticleEntity.draftId,
                        Expressions.stringTemplate(
                                "coalesce({0}, {1})",
                                articleEntity.title,
                                draftEntity.title
                        ),
                        seriesArticleEntity.displayOrder,
                        seriesArticleEntity.createdAt,
                        seriesArticleEntity.updatedAt
                ))
                .from(seriesArticleEntity)
                .leftJoin(articleEntity).on(seriesArticleEntity.articleId.eq(articleEntity.id))
//                .leftJoin(draftEntity)
//                .on(
//                        seriesArticleEntity.draftId.eq(draftEntity.id)
////                                .and(draftEntity.articleId.isNull())
//                )
                .where(seriesArticleEntity.seriesId.eq(longSeriesId))
                .orderBy(seriesArticleEntity.displayOrder.asc());

        // 조건부로 draftEntity join 추가
        if (isOwner) {
            query.leftJoin(draftEntity)
                    .on(
                            seriesArticleEntity.draftId.eq(draftEntity.id)
                                    .and(draftEntity.articleId.isNull())
                    );
        }

        List<SeriesArticleSummaryProjection> articles = query.fetch();

        return Optional.ofNullable(
                mapper.toDetail(seriesDetail, articles)
        );
    }

    @Override
    public Optional<SeriesDetail> findExceptDraftsById(String seriesId) {
        long longSeriesId = Long.parseLong(seriesId);

        SeriesDetailProjection seriesDetail = querySeriesDetail(longSeriesId);
        if (seriesDetail == null) throw SERIES_NOT_FOUND.exception();

        List<SeriesArticleSummaryProjection> articles = getQuerydsl().createQuery()
                .select(Projections.constructor(
                        SeriesArticleSummaryProjection.class,
                        seriesArticleEntity.articleId,
                        seriesArticleEntity.draftId,
                        articleEntity.title,
                        seriesArticleEntity.displayOrder,
                        seriesArticleEntity.createdAt,
                        seriesArticleEntity.updatedAt
                ))
                .from(seriesArticleEntity)
                .leftJoin(articleEntity).on(seriesArticleEntity.articleId.eq(articleEntity.id))
                .where(
                        seriesArticleEntity.seriesId.eq(longSeriesId)
                                .and(seriesArticleEntity.articleId.isNotNull())
                )
                .orderBy(seriesArticleEntity.displayOrder.asc())
                .fetch();

        return Optional.ofNullable(
                mapper.toDetail(seriesDetail, articles)
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
                .fetch();
    }

    private SeriesDetailProjection querySeriesDetail(long seriesId) {
        return getQuerydsl().createQuery()
                .select(Projections.constructor(
                        SeriesDetailProjection.class,
                        seriesEntity.id,
                        seriesEntity.blogId,
                        seriesEntity.title,
                        seriesEntity.description,
                        seriesEntity.bannerUrl,
                        seriesEntity.displayOrder,
                        seriesEntity.createdAt,
                        seriesEntity.updatedAt
                ))
                .from(seriesEntity)
                .where(seriesEntity.id.eq(seriesId))
                .fetchOne();
    }
}
