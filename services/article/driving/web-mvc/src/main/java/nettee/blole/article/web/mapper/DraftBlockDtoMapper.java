package nettee.blole.article.web.mapper;

import nettee.blole.article.web.dto.DraftBlockCommandDto.DraftBlockCreateCommand;
import nettee.blole.article.web.dto.DraftBlockCommandDto.DraftBlockUpdateCommand;
import nettee.blolet.article.domain.DraftBlock;
import nettee.blolet.article.domain.sub.DraftBlockStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DraftBlockDtoMapper {
    DraftBlock toDomain(DraftBlockCreateCommand command, DraftBlockStatus status);
    DraftBlock toDomain(String id, DraftBlockUpdateCommand command);
}
