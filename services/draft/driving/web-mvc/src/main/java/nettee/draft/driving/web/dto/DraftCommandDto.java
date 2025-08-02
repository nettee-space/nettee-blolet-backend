package nettee.draft.driving.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import nettee.draft.domain.Draft;
import nettee.draft.domain.type.DraftStatus;

public final class DraftCommandDto {
    private DraftCommandDto() {

    }

    @Builder
    public record DraftCreateCommand(
            @NotNull(message = "블로그ID를 입력하십시오.")
            String blogId,
            String articleId,
            @NotBlank(message = "제목을 입력하십시오.")
            @Size(min = 3, message = "제목은 세 글자 이상 입력하세요.")
            String title,
            @NotBlank(message = "본문을 입력하십시오")
            @Size(min = 3, message = "본문은 세 글자 이상 입력하세요.")
            String content,
            String path
    ) {
    }

    @Builder
    public record DraftUpdateCommand(
            @NotBlank(message = "제목을 입력하십시오.")
            @Size(min = 3, message = "제목은 세 글자 이상 입력하세요.")
            String title,
            @NotBlank(message = "본문을 입력하십시오")
            @Size(min = 3, message = "본문은 세 글자 이상 입력하세요.")
            String content,
            String path,
            @NotNull(message = "상태를 입력하십시오")
            DraftStatus status
    ) {
    }

    @Builder
    public record DraftCommandResponse(
            Draft draft
    ) {

    }
}
