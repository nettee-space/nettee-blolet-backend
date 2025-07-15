package nettee.draft.readmodel;

import lombok.Builder;
import nettee.draft.domain.type.DraftStatus;

import java.time.Instant;
public final class DraftQueryModels {
    private DraftQueryModels() {
    }

    @Builder
    public record DraftDetail(
            Long id,
            Long blogId,
            Long articleId,
            Long entryBlockId,
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
            Long id,
            Long blogId,
            String title,
            String path,
            DraftStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
    }
}
