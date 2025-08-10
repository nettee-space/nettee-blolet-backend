package nettee.draft.draftblock.driven.rdb.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nettee.draft.draftblock.driven.rdb.entity.type.DraftBlockEntityStatus;
import nettee.draft.draftblock.driven.rdb.entity.type.DraftBlockEntityStatusConverter;
import nettee.jpa.support.SnowflakeBaseTimeEntity;

import java.util.Objects;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(schema = "article", name = "draft_block")
public class DraftBlockEntity extends SnowflakeBaseTimeEntity {
    private Long blogId;
    private Long draftId;
    private Long articleId;
    private Long nextBlockId;
    private String type;
    public String content;

    @Convert(converter = DraftBlockEntityStatusConverter.class)
    public DraftBlockEntityStatus status;

    @Builder(
            builderClassName = "UpdateDraftBlockEntityBuilder",
            builderMethodName = "prepareDraftBlockEntityUpdate",
            buildMethodName = "update"
    )
    public void update(String content, Long blogId, Long draftId, Long articleId, Long nextBlockId, String type, DraftBlockEntityStatus status) {
        Objects.requireNonNull(content, "Content cannot be null");
        Objects.requireNonNull(status, "status cannot be null");

        this.content = content;
        this.blogId = blogId;
        this.draftId = draftId;
        this.articleId = articleId;
        this.nextBlockId = nextBlockId;
        this.status = status;
        this.type = type;
    }

    @Builder(
            builderClassName = "UpdateStatusDraftBlockEntityBuilder",
            builderMethodName = "prepareDraftBlockEntityStatusUpdate",
            buildMethodName = "updateStatus"
    )
    public void updateStatus(DraftBlockEntityStatus status) {
        Objects.requireNonNull(status, "status cannot be null");
        this.status = status;
    }
}
