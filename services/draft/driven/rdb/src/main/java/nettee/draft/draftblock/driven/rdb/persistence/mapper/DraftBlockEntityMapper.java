package nettee.draft.draftblock.driven.rdb.persistence.mapper;

import nettee.draft.draftblock.domain.DraftBlock;
import nettee.draft.draftblock.driven.rdb.entity.DraftBlockEntity;
import nettee.draft.draftblock.readmodel.DraftBlockQueryModels.DraftBlockDetail;
import nettee.draft.draftblock.readmodel.DraftBlockQueryModels.DraftBlockSummary;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface DraftBlockEntityMapper {
    DraftBlock toDomain(DraftBlockEntity draftEntity);
    DraftBlockEntity toEntity(DraftBlock draft);
    DraftBlockDetail toDraftBlockDetail(DraftBlockEntity draftEntity);
    DraftBlockSummary toDraftBlockSummary(DraftBlockEntity draftEntity);

    default Optional<DraftBlock> toOptionalDomain(DraftBlockEntity draftEntity) {
        return Optional.ofNullable(toDomain(draftEntity));
    }

    default Optional<DraftBlockDetail> toOptionalDraftBlockDetail(DraftBlockEntity draftEntity) {
        return Optional.ofNullable(toDraftBlockDetail(draftEntity));
    }

    default Optional<DraftBlockSummary> toOptionalDraftBlockSummary(DraftBlockEntity draftEntity) {
        return Optional.ofNullable(toDraftBlockSummary(draftEntity));
    }

}
