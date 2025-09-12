package nettee.profile.rdb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nettee.jpa.support.SnowflakeBaseTimeEntity;

@Entity
@Table(schema = "profile", name = "profile")
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileEntity extends SnowflakeBaseTimeEntity {

    private String nickname;
    private String job;

    private Long userId;
}
