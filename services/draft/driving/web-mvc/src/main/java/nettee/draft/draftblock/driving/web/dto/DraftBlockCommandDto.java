package nettee.draft.draftblock.driving.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import nettee.draft.domain.Draft;
import nettee.draft.domain.type.DraftStatus;
import nettee.draft.draftblock.domain.DraftBlock;
import nettee.draft.draftblock.domain.type.DraftBlockStatus;

import java.util.Map;

public final class DraftBlockCommandDto {
    private DraftBlockCommandDto() {

    }

    @Builder
    public record DraftBlockCreateCommand(
            @NotNull(message = "블로그ID를 입력하십시오.")
            Long blogId,
            @NotNull(message = "임시글ID를 입력하십시오.")
            Long draftId,
            Long articleId,
            Long nextBlockId,
            @NotBlank(message = "블록 종류를 입력하십시오.")
            String type,
            @NotBlank(message = "블록 내용을 입력하십시오.")
            String content,
            Map<String, Object> style
    ) {
    }

    @Builder
    public record DraftBlockUpdateCommand(
            @NotNull(message = "id를 입력하십시오.")
            Long id,
            Long nextBlockId, // 순서/블록 연결성 변경시 사용
            @NotBlank(message = "블록 종류를 입력하십시오.")
            String type,
            @NotBlank(message = "블록 내용을 입력하십시오.")
            String content,
            Map<String, Object> style,
            @NotNull(message = "상태를 입력하십시오.")
            DraftBlockStatus status
    ) {
    }

    @Builder
    public record DraftBlockCommandResponse(
            DraftBlock draftblock
    ) {

    }
}
