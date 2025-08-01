package nettee.draft.draftblock.driving.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("draft-blocks")
@RequiredArgsConstructor
@Tag(name = "DraftBlock", description = "DraftBlock API")
public class DraftBlockCommandApi {
    private final DraftBlockCreateUseCase draftCreateUseCase;
    private final DraftBlockUpdateUseCase draftUpdateUseCase;
    private final DraftBlockDeleteUseCase draftDeleteUseCase;
    private final DraftBlockDtoMapper mapper;

    @Operation(summary = "블록 생성", description = "블록을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DraftBlockCommandResponse create(@RequestBody @Valid DraftBlockCreateCommand draftBlockCreateCommand) {
        var draft = DraftBlock.of(
                draftBlockCreateCommand.blogId(),
                draftBlockCreateCommand.draftId(),
                draftBlockCreateCommand.articleId(),
                draftBlockCreateCommand.type(),
                draftBlockCreateCommand.content(),
                draftBlockCreateCommand.style()
        );

        return DraftBlockCommandResponse.builder()
                .draftblock(draftCreateUseCase.createDraftBlock(draft))
                .build();
    }

    @Operation(summary = "블록 수정", description = "블록을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DraftBlockCommandResponse updateDraftBlock(
            @PathVariable("id") String id,
            @RequestBody @Valid DraftBlockUpdateCommand draftUpdateCommand
    ) {
        var draft = mapper.toDomain(id, draftUpdateCommand);

        return DraftBlockCommandResponse.builder()
                .draftblock(draftUpdateUseCase.updateDraftBlock(draft))
                .build();
    }

    @Operation(summary = "블록 삭제", description = "블록을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "성공")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBoard(@PathVariable("id") String id) {
        draftDeleteUseCase.deleteDraftBlock(id);
    }

}
