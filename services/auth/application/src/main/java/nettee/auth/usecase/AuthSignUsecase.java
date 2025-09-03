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

    void sendPasswordResetEmail(String email);
    void resetPassword(String email, String newPassword, String nonce);
    String verifyPassword(String userId, String password);
    void changePassword(String userId, String password, String nonce);

    LoginTokenModel refreshAccessToken(String userId, String refreshToken);
}
