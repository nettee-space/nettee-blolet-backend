package nettee.draft.draftblock.driving.web.mapper;

import nettee.draft.draftblock.domain.DraftBlock;
import nettee.draft.draftblock.domain.type.DraftBlockStatus;
import nettee.draft.draftblock.driving.web.dto.DraftBlockCommandDto.DraftBlockCreateCommand;
import nettee.draft.draftblock.driving.web.dto.DraftBlockCommandDto.DraftBlockUpdateCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DraftBlockDtoMapper {
    DraftBlock toDomain(DraftBlockCreateCommand command, DraftBlockStatus status);
    DraftBlock toDomain(String id, DraftBlockUpdateCommand command);
}
