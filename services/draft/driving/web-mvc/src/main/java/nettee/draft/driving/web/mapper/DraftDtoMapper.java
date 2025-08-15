package nettee.draft.driving.web.mapper;

import nettee.draft.domain.Draft;
import nettee.draft.domain.type.DraftStatus;
import nettee.draft.readmodel.DraftReadModels.DraftDetail;
import nettee.draft.driving.web.dto.DraftCommandDto.DraftCreateCommand;
import nettee.draft.driving.web.dto.DraftCommandDto.DraftUpdateCommand;
import nettee.draft.driving.web.dto.DraftQueryDto.DraftDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface DraftDtoMapper {
    @Mapping(target = "id", source = "userId")
    Draft toDomain(String userId, DraftCreateCommand command, DraftStatus status);
    Draft toDomain(String id, DraftUpdateCommand command);
    DraftDetailResponse toDtoDetail(Optional<DraftDetail> board);
}
