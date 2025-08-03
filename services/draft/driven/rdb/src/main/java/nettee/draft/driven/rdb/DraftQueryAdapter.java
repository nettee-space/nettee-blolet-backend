package nettee.draft.driven.rdb;

import com.querydsl.core.types.Projections;
import nettee.draft.readmodel.DraftReadModels.DraftDetail;
import nettee.draft.readmodel.DraftReadModels.DraftSummary;
import nettee.draft.driven.rdb.entity.DraftEntity;
import nettee.draft.driven.rdb.entity.type.DraftEntityStatus;
import nettee.draft.driven.rdb.persistence.mapper.DraftEntityMapper;
import nettee.draft.application.port.DraftQueryPort;
import nettee.draft.domain.type.DraftStatus;
import nettee.draft.readmodel.DraftReadModels.DraftTitle;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static nettee.draft.driven.rdb.entity.QDraftEntity.draftEntity;

@Repository
public class DraftQueryAdapter extends QuerydslRepositorySupport implements DraftQueryPort {
    private final DraftEntityMapper draftEntityMapper;
    private final Map<Set<DraftStatus>, Set<DraftEntityStatus>> statusMap = new ConcurrentHashMap<>();

    public DraftQueryAdapter(
            DraftEntityMapper draftEntityMapper) {
        super(DraftEntity.class);
        this.draftEntityMapper = draftEntityMapper;
    }

    @Override
    public Optional<DraftDetail> findById(String id) {
        Long longId = Long.parseLong(id);
        return draftEntityMapper.toOptionalDraftDetail(
                getQuerydsl().createQuery()
                        .select(draftEntity)
                        .from(draftEntity)
                        .where(draftEntity.id.eq(longId))
                        .fetchOne()
        );
    }

    @Override
    public Map<String, DraftTitle> findTitlesById(Set<String> ids) {
        Set<Long> longIds = ids.stream()
                .map(Long::parseLong)
                .collect(Collectors.toSet());

        List<DraftTitle> titles = getQuerydsl().createQuery()
                .select(Projections.constructor(
                        DraftTitle.class,
                        draftEntity.id.stringValue(),
                        draftEntity.title
                ))
                .from(draftEntity)
                .where(draftEntity.id.in(longIds))
                .fetch();

        return titles.stream()
                .collect(Collectors.toMap(DraftTitle::id, Function.identity(), (a, b) -> a));
    }

        @Override
    public List<DraftSummary> findByStatuses(String blogId, Set<DraftStatus> statuses, String sortBy, boolean ascending) {
        Long longBlogId = Long.parseLong(blogId);
        var draftEntityStatuses = statusMap.computeIfAbsent(
                statuses,
                (ignore) -> statuses.stream()
                        .map(DraftEntityStatus::valueOf)
                        .collect(Collectors.toSet())
        );

        var query = getQuerydsl().createQuery()
                .select(draftEntity)
                .from(draftEntity)
                .where(draftEntity.blogId.eq(longBlogId)
                        .and(draftEntity.status.in(draftEntityStatuses)));

        switch (sortBy.toLowerCase()) {
            case "title" -> query.orderBy(ascending ? draftEntity.title.asc() : draftEntity.title.desc());
            case "createdat" -> query.orderBy(ascending ? draftEntity.createdAt.asc() : draftEntity.createdAt.desc());
            case "updatedat" -> query.orderBy(ascending ? draftEntity.updatedAt.asc() : draftEntity.updatedAt.desc());
            default -> query.orderBy(draftEntity.createdAt.desc());
        }

        var result = query.fetch();

        return result.stream()
                .map(draftEntityMapper::toDraftSummary)
                .toList();
    }
}
