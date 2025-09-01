package nettee.draft.driving.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import nettee.draft.domain.Draft;
import nettee.draft.domain.DraftImage;
import nettee.draft.domain.type.DraftStatus;

import static nettee.common.validation.Preconditions.validateMin;
import static nettee.common.validation.Preconditions.validateNotBlank;
import static nettee.draft.exception.DraftErrorCode.DRAFT_BLOG_ID_REQUIRED;
import static nettee.draft.exception.DraftErrorCode.DRAFT_TITLE_MIN_LENGTH;

public final class DraftCommandDto {
    private DraftCommandDto() {

    }

    @Builder
    public record DraftCreateCommand(
            String blogId,
            String title
    ) {
        public DraftCreateCommand {
            validateNotBlank(blogId, DRAFT_BLOG_ID_REQUIRED);
            if (title == null) {
                title = "";
            }

            title = title.strip();
            validateMin(title, 3, DRAFT_TITLE_MIN_LENGTH);
        }
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
    ) {}

    @Builder
    public record DraftImageCreateResponse(
            DraftImage image
    ) {}
}
