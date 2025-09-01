package nettee.auth.port;

public interface AuthMailSender {
    void sendOtp(String email, String otp);
    void sendPasswordReset(String email, String passwordResetUrl);
}
