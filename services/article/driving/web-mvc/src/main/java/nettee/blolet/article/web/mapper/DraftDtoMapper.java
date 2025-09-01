package nettee.blolet.article.web.mapper;

import nettee.blolet.article.domain.Draft;
import nettee.blolet.article.domain.DraftImage;
import nettee.blolet.article.domain.sub.DraftStatus;
import nettee.blolet.article.readmodel.DraftReadModels.DraftDetail;
import nettee.blolet.article.web.dto.DraftCommandDto.DraftCreateCommand;
import nettee.blolet.article.web.dto.DraftCommandDto.DraftImageCreateResponse;
import nettee.blolet.article.web.dto.DraftCommandDto.DraftUpdateCommand;
import nettee.blolet.article.web.dto.DraftQueryDto.DraftDetailResponse;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface DraftDtoMapper {

    Draft toDomain(DraftCreateCommand command, DraftStatus status);
    Draft toDomain(String id, DraftUpdateCommand command);

    DraftDetailResponse toDtoDetail(Optional<DraftDetail> board);
    DraftImageCreateResponse toCreateResponse(DraftImage draftImage);
}
