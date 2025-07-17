package nettee.draft.draftblock.readmodel;

import lombok.Builder;
import nettee.draft.draftblock.domain.type.DraftBlockStatus;

import java.time.Instant;

public final class DraftBlockReadModels {
    private DraftBlockReadModels() {
    }

    @Builder
    public record DraftBlockDetail(
            String id,
            String blogId,
            String draftId,
            String articleId,
            String nextBlockId,
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
            String id,
            String type,
            DraftBlockStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
    }
}
