package nettee.draft.draftblock.driven.rdb;

import lombok.RequiredArgsConstructor;
import nettee.draft.draftblock.application.port.DraftBlockCommandPort;
import nettee.draft.draftblock.domain.DraftBlock;
import nettee.draft.draftblock.domain.type.DraftBlockStatus;
import nettee.draft.draftblock.driven.rdb.entity.DraftBlockEntity;
import nettee.draft.draftblock.driven.rdb.entity.type.DraftBlockEntityStatus;
import nettee.draft.draftblock.driven.rdb.persistence.mapper.DraftBlockEntityMapper;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockDetail;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static nettee.draft.draftblock.exception.DraftBlockErrorCode.DEFAULT;
import static nettee.draft.draftblock.exception.DraftBlockErrorCode.DRAFT_BLOCK_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class DraftBlockCommandAdapter implements DraftBlockCommandPort {
    private final DraftBlockJpaRepository draftBlockJpaRepository;
    private final DraftBlockEntityMapper draftEntityMapper;

    @Override
    public Optional<DraftBlockDetail> findById(String id) {
        var draft = draftBlockJpaRepository.findById(id)
                .orElseThrow(DRAFT_BLOCK_NOT_FOUND::exception);
        return draftEntityMapper.toOptionalDraftBlockDetail(draft);
    }

    @Override
    public DraftBlock save(DraftBlock draft) {
        var draftEntity = draftEntityMapper.toEntity(draft);
        try{
            var newDraftBlock = draftBlockJpaRepository.save(draftEntity);
            draftBlockJpaRepository.flush();
            return draftEntityMapper.toDomain(newDraftBlock);
        } catch (DataAccessException e) {
            throw DEFAULT.exception(e);
        }
    }

    @Override
    public DraftBlock update(DraftBlock draft) {
        var existDraftBlock = draftBlockJpaRepository.findById(draft.getId())
                            .orElseThrow(DRAFT_BLOCK_NOT_FOUND::exception);
        DraftBlockEntity converted = draftEntityMapper.toEntity(draft);
        existDraftBlock.prepareDraftBlockEntityUpdate()
                .content(converted.getContent())
                .nextBlockId(converted.getNextBlockId())
                .type(converted.getType())
                .status(converted.getStatus())
                .update();

        return draftEntityMapper.toDomain(draftBlockJpaRepository.save(existDraftBlock));
    }

    @Override
    public void updateStatus(String id, DraftBlockStatus draftStatus) {
        var draft = draftBlockJpaRepository.findById(id)
                    .orElseThrow(DRAFT_BLOCK_NOT_FOUND::exception);
        draft.prepareDraftBlockEntityStatusUpdate()
                .status(DraftBlockEntityStatus.valueOf(draftStatus))
                .updateStatus();
        draftBlockJpaRepository.save(draft);
    }
}
