package nettee.draft.driven.rdb.entity;

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
import nettee.draft.driven.rdb.entity.type.DraftEntityStatus;
import nettee.draft.driven.rdb.entity.type.DraftEntityStatusConverter;
import nettee.jpa.support.LongBaseTimeEntity;
import nettee.jpa.support.SnowflakeBaseTimeEntity;
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
@Table(schema = "article", name = "draft")
public class DraftEntity extends SnowflakeBaseTimeEntity {
    public String title;
    public String content;
    public Long blogId;
    public Long articleId;
    public Long entryBlockId;
    public String path;

    @Convert(converter = DraftEntityStatusConverter.class)
    public DraftEntityStatus status;

    @Builder
    public DraftEntity(String title, String content, Long blogId, Long articleId, Long entryBlockId, String path, DraftEntityStatus status) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.blogId = blogId;
        this.articleId = articleId;
        this.entryBlockId = entryBlockId;
        this.path = path;
    }

    @Builder(
            builderClassName = "updateDraftEntityBuilder",
            builderMethodName = "prepareDraftEntityUpdate",
            buildMethodName = "update"
    )
    public void update(String title, String content, Long blogId, Long articleId, Long entryBlockId, String path, DraftEntityStatus status) {
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(content, "Content cannot be null");
        Objects.requireNonNull(status, "status cannot be null");

        this.title = title;
        this.content = content;
        this.status = status;
        this.blogId = blogId;
        this.articleId = articleId;
        this.entryBlockId = entryBlockId;
        this.path = path;
    }

    @Builder(
            builderClassName = "updateStatusDraftEntityBuilder",
            builderMethodName = "prepareDraftEntityStatusUpdate",
            buildMethodName = "updateStatus"
    )
    public void updateStatus(DraftEntityStatus status) {
        Objects.requireNonNull(status, "status cannot be null");

        this.status = status;
    }
}
