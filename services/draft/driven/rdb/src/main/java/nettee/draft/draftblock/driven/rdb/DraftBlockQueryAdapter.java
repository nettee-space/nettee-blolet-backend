package nettee.draft.draftblock.driven.rdb;

import nettee.draft.draftblock.application.port.DraftBlockQueryPort;
import nettee.draft.draftblock.domain.type.DraftBlockStatus;
import nettee.draft.draftblock.driven.rdb.entity.DraftBlockEntity;
import nettee.draft.draftblock.driven.rdb.entity.type.DraftBlockEntityStatus;
import nettee.draft.draftblock.driven.rdb.persistence.mapper.DraftBlockEntityMapper;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockDetail;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockSummary;
import nettee.draft.driven.rdb.entity.type.DraftEntityStatus;
import nettee.draft.exception.DraftErrorCode;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
    public Optional<DraftBlockDetail> findById(String id) {
        Long longId = Long.parseLong(id);
        return draftBlockEntityMapper.toOptionalDraftBlockDetail(
                getQuerydsl().createQuery()
                        .select(draftBlockEntity)
                        .from(draftBlockEntity)
                        .where(draftBlockEntity.id.eq(longId))
                        .fetchOne()
        );
    }

    @Override
    public List<DraftBlockSummary> findByStatus(String articleId, DraftBlockStatus status) {
        Long longArticleId = Long.parseLong(articleId);
        DraftBlockEntityStatus draftBlockEntityStatus = DraftBlockEntityStatus.valueOf(status.name());

        var query = getQuerydsl().createQuery()
                .select(draftBlockEntity)
                .from(draftBlockEntity)
                .where(draftBlockEntity.articleId.eq(longArticleId)
                        .and(draftBlockEntity.status.eq(draftBlockEntityStatus))
                );

        var result = query.fetch();

        return result.stream()
                .map(draftBlockEntityMapper::toDraftBlockSummary)
                .toList();
    }
}