package nettee.draft.draftblock.driven.rdb;

import nettee.draft.draftblock.application.port.DraftBlockQueryPort;
import nettee.draft.draftblock.domain.type.DraftBlockStatus;
import nettee.draft.draftblock.driven.rdb.entity.DraftBlockEntity;
import nettee.draft.draftblock.driven.rdb.entity.type.DraftBlockEntityStatus;
import nettee.draft.draftblock.driven.rdb.persistence.mapper.DraftBlockEntityMapper;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockDetail;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static nettee.draft.draftblock.driven.rdb.entity.QDraftBlockEntity.draftBlockEntity;

@Repository
public class DraftBlockQueryAdapter extends QuerydslRepositorySupport implements DraftBlockQueryPort {
    private final DraftBlockEntityMapper draftBlockEntityMapper;
    private final Map<Set<DraftBlockStatus>, Set<DraftBlockEntityStatus>> statusMap = new ConcurrentHashMap<>();

    public DraftBlockQueryAdapter(
            DraftBlockEntityMapper draftBlockEntityMapper) {
        super(DraftBlockEntity.class);
        this.draftBlockEntityMapper = draftBlockEntityMapper;
    }

    @Override
    public Optional<DraftBlockDetail> findById(Long id) {
        return draftBlockEntityMapper.toOptionalDraftBlockDetail(
                getQuerydsl().createQuery()
                        .select(draftBlockEntity)
                        .from(draftBlockEntity)
                        .where(draftBlockEntity.id.eq(id)
                        ).fetchOne()
        );
    }

    @Override
    public Page<DraftBlockSummary> findAll(Pageable pageable) {
        var query = getQuerydsl().createQuery()
                .select(draftBlockEntity)
                .from(draftBlockEntity)
                .where();

        pageable.getSort().forEach(order ->
                query.orderBy(order.isAscending() ?
                        draftBlockEntity.createdAt.asc() :
                        draftBlockEntity.createdAt.desc())
        );

        var result = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        var totalCount  = getQuerydsl().createQuery()
                .select(draftBlockEntity.count())
                .from(draftBlockEntity)
                .where();

        return PageableExecutionUtils.getPage(
                result.stream()
                        .map(draftBlockEntityMapper::toDraftBlockSummary)
                        .toList(),
                pageable,
                totalCount::fetchOne
        );
    }

    @Override
    public Page<DraftBlockSummary> findByStatuses(Set<DraftBlockStatus> statuses, Pageable pageable) {
        var draftBlockEntityStatuses = statusMap.computeIfAbsent(
                statuses,
                (ignore) -> statuses.stream()
                        .map(DraftBlockEntityStatus::valueOf)
                        .collect(Collectors.toSet())
        );

        var query = getQuerydsl().createQuery()
                .select(draftBlockEntity)
                .from(draftBlockEntity)
                .where(draftBlockEntity.status.in(draftBlockEntityStatuses));

        pageable.getSort().forEach(order ->
                query.orderBy(order.isAscending() ?
                        draftBlockEntity.createdAt.asc() :
                        draftBlockEntity.createdAt.desc())
        );

        var result = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        var totalCount = getQuerydsl().createQuery()
                .select(draftBlockEntity.count())
                .from(draftBlockEntity)
                .where(draftBlockEntity.status.in(draftBlockEntityStatuses));

        return PageableExecutionUtils.getPage(
                result.stream()
                        .map(draftBlockEntityMapper::toDraftBlockSummary)
                        .toList(),
                pageable,
                totalCount::fetchOne
        );
    }
}