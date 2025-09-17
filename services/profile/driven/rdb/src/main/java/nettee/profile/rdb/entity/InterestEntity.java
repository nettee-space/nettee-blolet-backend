package nettee.profile.rdb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nettee.jpa.support.SnowflakeBaseTimeEntity;

@Entity
@Table(schema = "profile", name = "interest")
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InterestEntity extends SnowflakeBaseTimeEntity {
    private String interest;
}
