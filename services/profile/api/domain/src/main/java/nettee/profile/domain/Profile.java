package nettee.profile.domain;

import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    private String id;
    private String userId;
    private String nickname;
    private String job;
    private List<String> interests;

    private Instant createdAt;
    private Instant updatedAt;
}
