package nettee.profile.readmodel;

import java.util.List;

public final class ProfileCommandModels {
    public record ProfileCreateModel(
        String nickname,
        String job,
        List<String> interests
    ) {
    }
}
