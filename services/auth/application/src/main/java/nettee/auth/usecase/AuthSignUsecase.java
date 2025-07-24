package nettee.auth.usecase;

import nettee.auth.domain.User;

public interface AuthSignUsecase {
    void signUp(User user);
}
