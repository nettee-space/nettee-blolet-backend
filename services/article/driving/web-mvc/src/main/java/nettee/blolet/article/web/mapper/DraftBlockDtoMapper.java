package nettee.blolet.article.web.mapper;

import nettee.blolet.article.domain.DraftBlock;
import nettee.blolet.article.domain.sub.DraftBlockStatus;
import nettee.blolet.article.web.dto.DraftBlockCommandDto.DraftBlockCreateCommand;
import nettee.blolet.article.web.dto.DraftBlockCommandDto.DraftBlockUpdateCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DraftBlockDtoMapper {
    DraftBlock toDomain(DraftBlockCreateCommand command, DraftBlockStatus status);
    DraftBlock toDomain(String id, DraftBlockUpdateCommand command);
}
