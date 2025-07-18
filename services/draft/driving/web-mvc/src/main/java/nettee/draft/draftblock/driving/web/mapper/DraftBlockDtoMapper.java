package nettee.draft.draftblock.driving.web.mapper;

import nettee.draft.draftblock.domain.DraftBlock;
import nettee.draft.draftblock.driving.web.dto.DraftBlockCommandDto.DraftBlockCreateCommand;
import nettee.draft.draftblock.driving.web.dto.DraftBlockCommandDto.DraftBlockUpdateCommand;
import nettee.draft.draftblock.driving.web.dto.DraftBlockQueryDto.DraftBlockDetailResponse;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockDetail;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface DraftBlockDtoMapper {
    DraftBlock toDomain(DraftBlockCreateCommand command);
    DraftBlock toDomain(Long id, DraftBlockUpdateCommand command);
    DraftBlockDetailResponse toDtoDetail(Optional<DraftBlockDetail> board);
}
