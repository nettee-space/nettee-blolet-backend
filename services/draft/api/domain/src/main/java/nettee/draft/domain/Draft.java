package nettee.draft.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.draft.domain.type.DraftStatus;

import java.time.Instant;
import java.util.Objects;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Draft {
    private String id;
    private String blogId;
    private String articleId;
    private String entryBlockId;
    private String title;
    private String content;
    private String path;
    private DraftStatus status;
    private Instant createdAt;
    private Instant updatedAt;

    public static Draft of(String blogId, String articleId, String title, String content, String path) {
        return Draft.builder()
                .blogId(blogId)
                .articleId(articleId)
                .title(title)
                .content(content)
                .path(path)
                .status(DraftStatus.PENDING) // 기본 상태 설정
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    @Builder(
            builderClassName = "UpdateDraftBuilder",
            builderMethodName = "prepareDraftUpdate",
            buildMethodName = "update"
    )
    public void update(String title, String content, String path) {
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(content, "Content cannot be null");

        this.title = title;
        this.content = content;
        this.path = path;
        this.updatedAt = Instant.now();
    }

    public void softDelete() { this.status = DraftStatus.REMOVED; };
}