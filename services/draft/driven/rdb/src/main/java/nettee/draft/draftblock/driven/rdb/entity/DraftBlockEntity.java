package nettee.draft.draftblock.driven.rdb.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Objects;

@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(schema = "article", name = "draft_block")
@EntityListeners(AuditingEntityListener.class)
public class DraftBlockEntity {
    @Id
    private Long id; //snowflake로 수정예정
    private Long blogId;
    private Long draftId;
    private Long articleId;
    private Long nextBlockId;
    private String type;
    public String content;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    @Convert(converter = DraftBlockEntityStatusConverter.class)
    public DraftBlockEntityStatus status;

    @Builder
    public DraftBlockEntity(String content, Long blogId, Long draftId, Long articleId, Long nextBlockId, String type, DraftBlockEntityStatus status) {
        this.id = new java.util.Random().nextLong(); //임시 아이디. 필히 삭제!!
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
            builderClassName = "updateStatusDraftBlockEntityBuilder",
            builderMethodName = "prepareDraftBlockEntityStatusUpdate",
            buildMethodName = "updateStatus"
    )
    public void updateStatus(DraftBlockEntityStatus status) {
        Objects.requireNonNull(status, "status cannot be null");
        this.status = status;
    }
}
