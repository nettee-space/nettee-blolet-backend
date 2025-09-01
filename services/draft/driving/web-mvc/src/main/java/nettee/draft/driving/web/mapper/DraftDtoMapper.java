package nettee.draft.driving.web.mapper;

import nettee.draft.domain.Draft;
import nettee.draft.domain.DraftImage;
import nettee.draft.domain.type.DraftStatus;
import nettee.draft.driving.web.dto.DraftCommandDto.DraftCreateCommand;
import nettee.draft.driving.web.dto.DraftCommandDto.DraftImageCreateResponse;
import nettee.draft.driving.web.dto.DraftCommandDto.DraftUpdateCommand;
import nettee.draft.driving.web.dto.DraftQueryDto.DraftDetailResponse;
import nettee.draft.readmodel.DraftReadModels.DraftDetail;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface DraftDtoMapper {

    Draft toDomain(DraftCreateCommand command, DraftStatus status);
    Draft toDomain(String id, DraftUpdateCommand command);

    DraftDetailResponse toDtoDetail(Optional<DraftDetail> board);
    DraftImageCreateResponse toCreateResponse(DraftImage draftImage);
}
