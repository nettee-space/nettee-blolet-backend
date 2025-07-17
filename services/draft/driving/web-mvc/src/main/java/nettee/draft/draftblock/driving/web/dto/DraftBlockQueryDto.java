package nettee.draft.draftblock.driving.web.dto;

import lombok.Builder;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockDetail;

public class DraftBlockQueryDto {
    private DraftBlockQueryDto() {

    }

    @Builder
    public record DraftBlockDetailResponse(
            DraftBlockDetail draftBlockDetail
    ) {
    }
}
