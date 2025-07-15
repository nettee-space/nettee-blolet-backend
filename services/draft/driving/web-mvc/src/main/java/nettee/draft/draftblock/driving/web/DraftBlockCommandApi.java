package nettee.draft.draftblock.driving.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nettee.draft.draftblock.application.usecase.DraftBlockCreateUseCase;
import nettee.draft.draftblock.application.usecase.DraftBlockDeleteUseCase;
import nettee.draft.draftblock.application.usecase.DraftBlockUpdateUseCase;
import nettee.draft.draftblock.domain.DraftBlock;
import nettee.draft.draftblock.driving.web.dto.DraftBlockCommandDto.DraftBlockCommandResponse;
import nettee.draft.draftblock.driving.web.dto.DraftBlockCommandDto.DraftBlockCreateCommand;
import nettee.draft.draftblock.driving.web.dto.DraftBlockCommandDto.DraftBlockUpdateCommand;
import nettee.draft.draftblock.driving.web.mapper.DraftBlockDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("draftBlocks")
@RequiredArgsConstructor
public class DraftBlockCommandApi {
    private final DraftBlockCreateUseCase draftCreateUseCase;
    private final DraftBlockUpdateUseCase draftUpdateUseCase;
    private final DraftBlockDeleteUseCase draftDeleteUseCase;
    private final DraftBlockDtoMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DraftBlockCommandResponse create(@RequestBody @Valid DraftBlockCreateCommand draftBlockCreateCommand) {
        var draft = DraftBlock.of(
                draftBlockCreateCommand.type(),
                draftBlockCreateCommand.content(),
                draftBlockCreateCommand.style()
        );

        return DraftBlockCommandResponse.builder()
                .draftblock(draftCreateUseCase.createDraftBlock(draft))
                .build();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DraftBlockCommandResponse updateDraftBlock(
            @PathVariable("id") Long id,
            @RequestBody @Valid DraftBlockUpdateCommand draftUpdateCommand
    ) {
        var draft = mapper.toDomain(id, draftUpdateCommand);

        return DraftBlockCommandResponse.builder()
                .draftblock(draftUpdateUseCase.updateDraftBlock(draft))
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBoard(@PathVariable("id") Long id) {
        draftDeleteUseCase.deleteDraftBlock(id);
    }

}
