package nettee.auth.port;

public interface MailSender {
    void sendOtp(String email, String otp);
}
