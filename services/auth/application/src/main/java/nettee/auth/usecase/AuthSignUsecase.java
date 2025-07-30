package nettee.auth.usecase;

import nettee.blolet.auth.readmodel.AuthCommandModels.LoginTokenModel;
import nettee.blolet.auth.readmodel.AuthCommandModels.SignUpRequestModel;

public interface AuthSignUsecase {
    void signUp(SignUpRequestModel signUpRequest);
    LoginTokenModel signIn(String loginId, String password);
}
