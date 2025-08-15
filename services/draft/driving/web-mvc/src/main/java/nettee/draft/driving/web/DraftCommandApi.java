package nettee.draft.driving.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nettee.draft.application.usecase.DraftCreateUseCase;
import nettee.draft.application.usecase.DraftDeleteUseCase;
import nettee.draft.application.usecase.DraftUpdateUseCase;
import nettee.draft.domain.type.DraftStatus;
import nettee.draft.driving.web.dto.DraftCommandDto.DraftCommandResponse;
import nettee.draft.driving.web.dto.DraftCommandDto.DraftCreateCommand;
import nettee.draft.driving.web.dto.DraftCommandDto.DraftUpdateCommand;
import nettee.draft.driving.web.mapper.DraftDtoMapper;
import nettee.jwt.annotation.AuthUser;
import nettee.jwt.annotation.AuthorizedUser;
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
@RequestMapping("drafts")
@RequiredArgsConstructor
@Tag(name = "Draft", description = "Draft API")
public class DraftCommandApi {
    private final DraftCreateUseCase draftCreateUseCase;
    private final DraftUpdateUseCase draftUpdateUseCase;
    private final DraftDeleteUseCase draftDeleteUseCase;
    private final DraftDtoMapper mapper;

    @Operation(summary = "임시 아티클 생성", description = "임시 아티클을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DraftCommandResponse create(
            @RequestBody @Valid DraftCreateCommand draftCreateCommand,
            @AuthUser AuthorizedUser user
    ) {
        var draft = mapper.toDomain(draftCreateCommand, DraftStatus.PENDING);

        return DraftCommandResponse.builder()
                .draft(draftCreateUseCase.createDraft(user.userId(), draft))
                .build();
    }

    @Operation(summary = "임시 아티클 수정", description = "임시아티클 ID로 임시 아티클을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DraftCommandResponse updateDraft(
            @PathVariable("id") String id,
            @RequestBody @Valid DraftUpdateCommand draftUpdateCommand
    ) {
        var draft = mapper.toDomain(id, draftUpdateCommand);

        return DraftCommandResponse.builder()
                .draft(draftUpdateUseCase.updateDraft(draft))
                .build();
    }

    @Operation(summary = "임시 아티클 삭제", description = "임시아티클 ID로 임시 아티클을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "성공")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBoard(@PathVariable("id") String id) {
        draftDeleteUseCase.deleteDraft(id);
    }

}
