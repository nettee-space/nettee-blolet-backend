package nettee.draft.driven.rdb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nettee.jpa.support.SnowflakeBaseTimeEntity;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(schema = "article", name = "draft_image")
public class DraftImageEntity extends SnowflakeBaseTimeEntity {

    public String imageUrl;
}
