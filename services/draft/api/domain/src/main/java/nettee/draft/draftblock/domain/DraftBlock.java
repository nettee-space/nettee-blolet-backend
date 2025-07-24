package nettee.draft.draftblock.domain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.draft.draftblock.domain.type.DraftBlockStatus;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DraftBlock {
    private String id;
    private String blogId;
    private String draftId;
    private String articleId;
    private String nextBlockId;
    private String type;
    private String content;
    private Map<String, Object> style;
    private DraftBlockStatus status;
    private Instant createdAt;
    private Instant updatedAt;

    public static DraftBlock of(String blogId, String draftId, String articleId, String type, String content, Map<String, Object> style) {
        return DraftBlock.builder()
                .blogId(blogId)
                .draftId(draftId)
                .articleId(articleId)
                .type(type)
                .content(content)
                .style(style)
                .status(DraftBlockStatus.DRAFT) // 기본 상태
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    @Builder(
            builderClassName = "UpdateDraftBlockBuilder",
            builderMethodName = "prepareDraftBlockUpdate",
            buildMethodName = "update"
    )
    public void update(String content, Map<String, Object> style) {
        Objects.requireNonNull(content, "Content cannot be null");

        this.content = content;
        this.style = style;
        this.updatedAt = Instant.now();
    }

    public void softDelete() { this.status = DraftBlockStatus.DELETED; }
}
