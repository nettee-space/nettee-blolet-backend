package nettee.auth.usecase;

import nettee.blolet.auth.readmodel.AuthCommandModels.LoginTokenModel;
import nettee.blolet.auth.readmodel.AuthCommandModels.SignUpRequestModel;

public interface AuthSignUsecase {
    LoginTokenModel signUp(SignUpRequestModel signUpRequest);
    LoginTokenModel signIn(String loginId, String password);
}
