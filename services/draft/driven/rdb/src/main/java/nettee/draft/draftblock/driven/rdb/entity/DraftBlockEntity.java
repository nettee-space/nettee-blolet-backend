package nettee.draft.draftblock.driven.rdb.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.draft.draftblock.driven.rdb.entity.type.DraftBlockEntityStatus;
import nettee.draft.draftblock.driven.rdb.entity.type.DraftBlockEntityStatusConverter;
import nettee.jpa.support.LongBaseTimeEntity;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Objects;

@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "draftBlock")
public class DraftBlockEntity extends LongBaseTimeEntity {
    public String title;
    public String content;

    @Convert(converter = DraftBlockEntityStatusConverter.class)
    public DraftBlockEntityStatus status;

    @Builder
    public DraftBlockEntity(String title, String content, DraftBlockEntityStatus status) {
        this.title = title;
        this.content = content;
        this.status = status;
    }

    @Builder(
            builderClassName = "updateDraftBlockEntityBuilder",
            builderMethodName = "prepareDraftBlockEntityUpdate",
            buildMethodName = "update"
    )
    public void update(String title, String content, Integer totalLikes, Integer totalViews, Integer totalShares) {
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(content, "Content cannot be null");
        Objects.requireNonNull(status, "status cannot be null");

        this.title = title;
        this.content = content;
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
