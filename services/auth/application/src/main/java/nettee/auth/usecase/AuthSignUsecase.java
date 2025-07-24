package nettee.auth.usecase;

import nettee.auth.domain.User;
import nettee.series.readmodel.AuthCommandModels.LoginToken;

public interface AuthSignUsecase {
    LoginToken signUp(User user);
}
