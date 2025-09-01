package nettee.blole.article.web.dto;

import lombok.Builder;
import nettee.blolet.article.readmodel.DraftReadModels.DraftDetail;
import nettee.blolet.article.readmodel.DraftReadModels.DraftTitle;

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
