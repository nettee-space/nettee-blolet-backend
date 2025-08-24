package nettee.draft.driven.rdb.persistence.mapper;

import nettee.draft.domain.DraftImage;
import nettee.draft.driven.rdb.entity.DraftEntity;
import nettee.draft.driven.rdb.entity.DraftImageEntity;
import nettee.draft.readmodel.DraftReadModels.DraftDetail;
import nettee.draft.readmodel.DraftReadModels.DraftSummary;
import nettee.draft.domain.Draft;
import nettee.draft.readmodel.DraftReadModels.DraftTitle;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface DraftEntityMapper {

    Draft toDomain(DraftEntity draftEntity);
    DraftEntity toEntity(Draft draft);

    DraftDetail toDraftDetail(DraftEntity draftEntity);
    DraftSummary toDraftSummary(DraftEntity draftEntity);
    DraftTitle toDraftTitle(DraftEntity draftEntity);

    DraftImage toDomain(DraftImageEntity draftImageEntity);
    DraftImageEntity toEntity(DraftImage draftImage);

    default Optional<Draft> toOptionalDomain(DraftEntity draftEntity) {
        return Optional.ofNullable(toDomain(draftEntity));
    }

    default Optional<DraftDetail> toOptionalDraftDetail(DraftEntity draftEntity) {
        return Optional.ofNullable(toDraftDetail(draftEntity));
    }

    default Optional<DraftSummary> toOptionalDraftSummary(DraftEntity draftEntity) {
        return Optional.ofNullable(toDraftSummary(draftEntity));
    }

    default List<DraftTitle> toListDraftTitle(List<DraftEntity> draftEntities) {
        if (draftEntities == null || draftEntities.isEmpty()) {
            return List.of();
        }
        return draftEntities.stream()
                .map(this::toDraftTitle)
                .filter(Objects::nonNull)
                .toList();
    }
}
