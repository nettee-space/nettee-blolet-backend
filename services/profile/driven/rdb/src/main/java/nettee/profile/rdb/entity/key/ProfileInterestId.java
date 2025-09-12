package nettee.profile.rdb.entity.key;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProfileInterestId implements Serializable {
    private Long profileId;
    private Long interestId;
}


