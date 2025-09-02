package nettee.blolet.article.rdb.mapper;

import nettee.blolet.article.domain.DraftBlock;
import nettee.blolet.article.readmodel.DraftBlockReadModels.DraftBlockDetail;
import nettee.blolet.article.readmodel.DraftBlockReadModels.DraftBlockSummary;
import nettee.blolet.article.rdb.entity.DraftBlockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface DraftBlockEntityMapper {
    DraftBlock toDomain(DraftBlockEntity draftEntity);
    @Mapping(target = "nextBlockId", expression = "java(parseNextBlockId(draft.getNextBlockId()))")
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

    default Long parseNextBlockId(String raw) {
        if (raw == null || raw.isBlank()) return null;
        return Long.parseLong(raw);
    }
}
