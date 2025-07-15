package nettee.draft.draftblock.readmodel;

import lombok.Builder;
import nettee.draft.draftblock.domain.type.DraftBlockStatus;

import java.time.Instant;

public final class DraftBlockQueryModels {
    private DraftBlockQueryModels() {
    }

    @Builder
    public record DraftBlockDetail(
            Long id,
            Long blogId,
            Long draftId,
            Long articleId,
            Long nextBlockId,
            String type,
            String content,
            Object style,
            DraftBlockStatus status,
            Instant createdAt,
            Instant updatedAt

    ) {
    }
    @Builder
    public record DraftBlockSummary(
            Long id,
            String type,
            DraftBlockStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
    }
}
