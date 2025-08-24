package nettee.draft.driven.rdb;

import lombok.RequiredArgsConstructor;
import nettee.draft.domain.DraftImage;
import nettee.draft.driven.rdb.entity.DraftImageEntity;
import nettee.draft.readmodel.DraftReadModels.DraftDetail;
import nettee.draft.domain.Draft;
import nettee.draft.driven.rdb.entity.type.DraftEntityStatus;
import nettee.draft.driven.rdb.persistence.mapper.DraftEntityMapper;
import nettee.draft.application.port.DraftCommandPort;
import nettee.draft.domain.type.DraftStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static nettee.draft.exception.DraftErrorCode.DEFAULT;
import static nettee.draft.exception.DraftErrorCode.DRAFT_IMAGE_SAVE_FAILED;
import static nettee.draft.exception.DraftErrorCode.DRAFT_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class DraftCommandAdapter implements DraftCommandPort {

    private final DraftJpaRepository draftJpaRepository;
    private final DraftImageJpaRepository draftImageJpaRepository;
    private final DraftEntityMapper mapper;

    @Override
    public Optional<DraftDetail> findById(String id) {
        var draft = draftJpaRepository.findById(id)
                .orElseThrow(DRAFT_NOT_FOUND::exception);
        return mapper.toOptionalDraftDetail(draft);
    }

    @Override
    public Draft save(Draft draft) {
        var draftEntity = mapper.toEntity(draft);

        try {
            var newDraft = draftJpaRepository.save(draftEntity);
            return mapper.toDomain(newDraft);
        } catch (DataAccessException e) {
            throw DEFAULT.exception(e);
        }
    }

    @Override
    public Draft update(Draft draft) {
        var existDraft = draftJpaRepository.findById(draft.getId())
                            .orElseThrow(DRAFT_NOT_FOUND::exception);

        existDraft.prepareDraftEntityUpdate()
                .title(draft.getTitle())
                .content(draft.getContent())
                .path(draft.getPath())
                .status(DraftEntityStatus.valueOf(draft.getStatus()))
                .update();

        return mapper.toDomain(draftJpaRepository.save(existDraft));
    }

    @Override
    public void updateStatus(String id, DraftStatus draftStatus) {
        var draft = draftJpaRepository.findById(id)
                    .orElseThrow(DRAFT_NOT_FOUND::exception);

        draft.prepareDraftEntityStatusUpdate()
                .status(DraftEntityStatus.valueOf(draftStatus))
                .updateStatus();

        draftJpaRepository.save(draft);
    }

    @Override
    public DraftImage save(DraftImage draftImage) {
        DraftImageEntity draftImageEntity = mapper.toEntity(draftImage);

        try {
            var newDraftImage= draftImageJpaRepository.save(draftImageEntity);
            return mapper.toDomain(newDraftImage);
        } catch (DataAccessException e) {
            throw DRAFT_IMAGE_SAVE_FAILED.exception(e);
        }
    }
}
