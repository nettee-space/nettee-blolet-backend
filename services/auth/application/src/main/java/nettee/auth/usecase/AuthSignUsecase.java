package nettee.auth.usecase;

import nettee.blolet.auth.readmodel.AuthCommandModels.LoginTokenModel;
import nettee.blolet.auth.readmodel.AuthCommandModels.SignUpRequestModel;

public interface AuthSignUsecase {
    LoginTokenModel signUp(SignUpRequestModel signUpRequest);
    LoginTokenModel signIn(String loginId, String password);

    String sendOtp(String email);
    String verifyOtp(String email, String otp, String nonce);

    void logout(String userId, String refreshToken);
    void withdraw(String userId, String refreshToken);

    LoginTokenModel refreshAccessToken(String userId, String refreshToken);
}
