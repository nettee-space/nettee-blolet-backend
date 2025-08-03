package nettee.draft.readmodel;

import lombok.Builder;
import nettee.draft.domain.type.DraftStatus;

import java.time.Instant;
public final class DraftReadModels {
    private DraftReadModels() {
    }

    @Builder
    public record DraftDetail(
            String id,
            String blogId,
            String articleId,
            String entryBlockId,
            String title,
            String content,
            String path,
            DraftStatus status,
            Instant createdAt,
            Instant updatedAt

    ) {
    }

    @Builder
    public record DraftSummary(
            String id,
            String blogId,
            String title,
            String path,
            DraftStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
    }

    @Builder
    public record DraftTitle(
            String id,
            String title
    ) {
    }
}
