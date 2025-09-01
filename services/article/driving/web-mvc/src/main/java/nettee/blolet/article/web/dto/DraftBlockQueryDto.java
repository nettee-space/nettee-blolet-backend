package nettee.blolet.article.web.dto;

import lombok.Builder;
import nettee.blolet.article.readmodel.DraftBlockReadModels.DraftBlockDetail;

public class DraftBlockQueryDto {
    private DraftBlockQueryDto() {

    }

    @Builder
    public record DraftBlockDetailResponse(
            DraftBlockDetail draftBlockDetail
    ) {
    }
}
