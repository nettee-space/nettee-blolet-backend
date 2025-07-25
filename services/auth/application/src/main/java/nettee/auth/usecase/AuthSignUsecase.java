package nettee.auth.usecase;

import nettee.blolet.auth.readmodel.AuthCommandModels.SignUpRequestModel;

public interface AuthSignUsecase {
    void signUp(SignUpRequestModel signUpRequest);
}
