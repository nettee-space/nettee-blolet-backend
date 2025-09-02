package nettee.blolet.article.rdb.mapper;

import nettee.blolet.article.domain.Draft;
import nettee.blolet.article.domain.DraftImage;
import nettee.blolet.article.readmodel.DraftReadModels.DraftDetail;
import nettee.blolet.article.readmodel.DraftReadModels.DraftSummary;
import nettee.blolet.article.readmodel.DraftReadModels.DraftTitle;
import nettee.blolet.article.rdb.entity.DraftEntity;
import nettee.blolet.article.rdb.entity.DraftImageEntity;
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
