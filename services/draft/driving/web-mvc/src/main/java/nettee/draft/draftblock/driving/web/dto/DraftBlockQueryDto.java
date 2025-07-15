package nettee.draft.draftblock.driving.web.dto;

import lombok.Builder;
import nettee.draft.draftblock.readmodel.DraftBlockQueryModels.DraftBlockDetail;
import nettee.draft.readmodel.DraftQueryModels.DraftDetail;

public class DraftBlockQueryDto {
    private DraftBlockQueryDto() {

    }

    @Builder
    public record DraftBlockDetailResponse(

            DraftBlockDetail draftBlockDetail
    ) {
    }
}
