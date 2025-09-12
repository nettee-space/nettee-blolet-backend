package nettee.profile.rdb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nettee.profile.rdb.entity.key.ProfileInterestId;

@Entity
@Table(schema = "profile", name = "profile_interest")
@IdClass(ProfileInterestId.class)
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileInterestEntity {
    @Id
    private Long profileId;

    @Id
    private Long interestId;
}
