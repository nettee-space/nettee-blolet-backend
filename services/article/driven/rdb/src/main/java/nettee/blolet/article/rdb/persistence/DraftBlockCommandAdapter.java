package nettee.blolet.article.rdb.persistence;

import lombok.RequiredArgsConstructor;
import nettee.blolet.article.application.port.DraftBlockCommandPort;
import nettee.blolet.article.domain.DraftBlock;
import nettee.blolet.article.domain.sub.DraftBlockStatus;
import nettee.blolet.article.readmodel.DraftBlockReadModels.DraftBlockDetail;
import nettee.blolet.article.rdb.entity.DraftBlockEntity;
import nettee.blolet.article.rdb.entity.type.DraftBlockEntityStatus;
import nettee.blolet.article.rdb.mapper.DraftBlockEntityMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static nettee.blolet.article.exception.DraftBlockErrorCode.DEFAULT;
import static nettee.blolet.article.exception.DraftBlockErrorCode.DRAFT_BLOCK_NOT_FOUND;

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
