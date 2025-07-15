package nettee.draft.draftblock.driven.rdb;

import lombok.RequiredArgsConstructor;
import nettee.draft.draftblock.application.port.DraftBlockCommandPort;
import nettee.draft.draftblock.domain.DraftBlock;
import nettee.draft.draftblock.domain.type.DraftBlockStatus;
import nettee.draft.draftblock.driven.rdb.DraftBlockJpaRepository;
import nettee.draft.draftblock.driven.rdb.entity.type.DraftBlockEntityStatus;
import nettee.draft.draftblock.driven.rdb.persistence.mapper.DraftBlockEntityMapper;
import nettee.draft.draftblock.readmodel.DraftBlockQueryModels.DraftBlockDetail;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static nettee.draft.draftblock.exception.DraftBlockCommandErrorCode.DEFAULT;
import static nettee.draft.draftblock.exception.DraftBlockCommandErrorCode.DRAFT_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class DraftBlockCommandAdapter implements DraftBlockCommandPort {
    private final DraftBlockJpaRepository draftJpaRepository;
    private final DraftBlockEntityMapper draftEntityMapper;

    @Override
    public Optional<DraftBlockDetail> findById(Long id) {
        var draft = draftJpaRepository.findById(id)
                .orElseThrow(DRAFT_NOT_FOUND::exception);
        return draftEntityMapper.toOptionalDraftBlockDetail(draft);
    }

    @Override
    public DraftBlock save(DraftBlock draft) {
        var draftEntity = draftEntityMapper.toEntity(draft);
        try{
            var newDraftBlock = draftJpaRepository.save(draftEntity);
            draftJpaRepository.flush();
            return draftEntityMapper.toDomain(newDraftBlock);
        } catch (DataAccessException e) {
            throw DEFAULT.exception(e);
        }
    }

    @Override
    public DraftBlock update(DraftBlock draft) {
        var existDraftBlock = draftJpaRepository.findById(draft.getId())
                            .orElseThrow(DRAFT_NOT_FOUND::exception);
        existDraftBlock.prepareDraftBlockEntityUpdate()
                .title(existDraftBlock.getTitle())
                .content(existDraftBlock.getContent())
                .update();

        return draftEntityMapper.toDomain(draftJpaRepository.save(existDraftBlock));
    }

    @Override
    public void updateStatus(Long id, DraftBlockStatus draftStatus) {
        var draft = draftJpaRepository.findById(id)
                    .orElseThrow(DRAFT_NOT_FOUND::exception);
        draft.prepareDraftBlockEntityStatusUpdate()
                .status(DraftBlockEntityStatus.valueOf(draftStatus))
                .updateStatus();
        draftJpaRepository.save(draft);
    }
}
