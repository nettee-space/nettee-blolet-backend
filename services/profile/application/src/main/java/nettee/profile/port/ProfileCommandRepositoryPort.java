package nettee.profile.port;

import nettee.profile.domain.Profile;

public interface ProfileCommandRepositoryPort {
    Profile save(Profile profile);

    boolean existsByUserId(String userId);
}
