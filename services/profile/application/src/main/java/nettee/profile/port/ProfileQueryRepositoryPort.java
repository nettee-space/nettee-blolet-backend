package nettee.profile.port;

import java.util.Optional;
import nettee.profile.domain.Profile;

public interface ProfileQueryRepositoryPort {
    Optional<Profile> findByUserId(String userId);
}
