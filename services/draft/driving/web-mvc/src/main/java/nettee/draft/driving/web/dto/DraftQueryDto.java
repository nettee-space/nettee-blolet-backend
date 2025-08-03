package nettee.draft.driving.web.dto;

import lombok.Builder;
import nettee.draft.readmodel.DraftReadModels.DraftDetail;
import nettee.draft.readmodel.DraftReadModels.DraftTitle;

import java.util.Map;

public class DraftQueryDto {
    private DraftQueryDto() {

    }

    @Builder
    public record DraftDetailResponse(
            DraftDetail draft
    ) {
    }

    @Builder
    public record DraftTitleResponse(
            Map<String, DraftTitle> draftTitles
    ) {
    }
}
