package nettee.blole.article.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nettee.blole.article.web.dto.DraftBlockCommandDto.DraftBlockCommandResponse;
import nettee.blole.article.web.dto.DraftBlockCommandDto.DraftBlockCreateCommand;
import nettee.blole.article.web.dto.DraftBlockCommandDto.DraftBlockUpdateCommand;
import nettee.blole.article.web.mapper.DraftBlockDtoMapper;
import nettee.blolet.article.application.usecase.DraftBlockCreateUseCase;
import nettee.blolet.article.application.usecase.DraftBlockDeleteUseCase;
import nettee.blolet.article.application.usecase.DraftBlockUpdateUseCase;
import nettee.blolet.article.domain.sub.DraftBlockStatus;
import nettee.blolet.jwt.filter.annotation.AuthUser;
import nettee.blolet.jwt.filter.annotation.AuthorizedUser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("draft-blocks")
@RequiredArgsConstructor
@Tag(name = "DraftBlock", description = "DraftBlock API")
public class DraftBlockCommandApi {
    private final DraftBlockCreateUseCase draftCreateUseCase;
    private final DraftBlockUpdateUseCase draftUpdateUseCase;
    private final DraftBlockDeleteUseCase draftDeleteUseCase;
    private final DraftBlockDtoMapper mapper;

    @Operation(summary = "블록 생성", description = "블록을 생성합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DraftBlockCommandResponse create(
            @RequestBody @Valid DraftBlockCreateCommand dto,
            @AuthUser AuthorizedUser user
    ) {
        var draft = mapper.toDomain(dto, DraftBlockStatus.PENDING);

        return DraftBlockCommandResponse.builder()
                .draftblock(draftCreateUseCase.create(user.userId(), draft))
                .build();
    }

    @Operation(summary = "블록 수정", description = "블록을 수정합니다.")
    @PutMapping("/{draftBlockId}")
    @ResponseStatus(HttpStatus.OK)
    public DraftBlockCommandResponse updateDraftBlock(
            @PathVariable("draftBlockId") String draftBlockId,
            @RequestBody @Valid DraftBlockUpdateCommand draftUpdateCommand,
            @AuthUser AuthorizedUser user
    ) {
        var draft = mapper.toDomain(draftBlockId, draftUpdateCommand);

        return DraftBlockCommandResponse.builder()
                .draftblock(draftUpdateUseCase.update(user.userId(), draft))
                .build();
    }

    @Operation(summary = "블록 삭제", description = "블록을 삭제합니다.")
    @DeleteMapping("/{draftBlockId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBoard(
            @PathVariable("draftBlockId") String draftBlockId,
            @AuthUser AuthorizedUser user
    ) {
        draftDeleteUseCase.delete(user.userId(), draftBlockId);
    }

}
