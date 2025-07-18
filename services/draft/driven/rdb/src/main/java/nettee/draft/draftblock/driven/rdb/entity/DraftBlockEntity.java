package nettee.draft.draftblock.driven.rdb.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.draft.draftblock.driven.rdb.entity.type.DraftBlockEntityStatus;
import nettee.draft.draftblock.driven.rdb.entity.type.DraftBlockEntityStatusConverter;
import nettee.jpa.support.LongBaseTimeEntity;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.Objects;

@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(schema = "article", name = "draft_block")
public class DraftBlockEntity {
    @Id
    private String id; //snowflake로 수정예정
    private String blogId;
    private String draftId;
    private String articleId;
    private String nextBlockId;
    private String type;
    public String content;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    @Convert(converter = DraftBlockEntityStatusConverter.class)
    public DraftBlockEntityStatus status;

    @Builder
    public DraftBlockEntity(String content, String blogId, String draftId, String articleId, String nextBlockId, String type, DraftBlockEntityStatus status) {
        this.id = java.util.UUID.randomUUID().toString(); //임시 아이디. 필히 삭제!!
        this.content = content;
        this.blogId = blogId;
        this.draftId = draftId;
        this.articleId = articleId;
        this.nextBlockId = nextBlockId;
        this.status = status;
        this.type = type;
    }

    @Builder(
            builderClassName = "updateDraftBlockEntityBuilder",
            builderMethodName = "prepareDraftBlockEntityUpdate",
            buildMethodName = "update"
    )
    public void update(String content, String blogId, String draftId, String articleId, String nextBlockId, String type, DraftBlockEntityStatus status) {
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
            builderClassName = "updateStatusDraftBlockEntityBuilder",
            builderMethodName = "prepareDraftBlockEntityStatusUpdate",
            buildMethodName = "updateStatus"
    )
    public void updateStatus(DraftBlockEntityStatus status) {
        Objects.requireNonNull(status, "status cannot be null");
        this.status = status;
    }
}
