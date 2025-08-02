package nettee.auth.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import nettee.auth.port.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService implements MailSender {

    private final JavaMailSender mailSender;

    @Override
    public void sendOtp(String email, String otp) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String htmlContent = "<h2>이메일 인증</h2>"
                        + "<p>아래의 인증 번호를 입력해주세요.</p>"
                        + "<p><b>" + otp + "</b></p>";

        try {
            helper.setTo(email);
            helper.setSubject("[Blolet] 이메일 인증");
            helper.setText(htmlContent, true);
        } catch (MessagingException e) {
            throw new RuntimeException("이메일 전송에 실패했습니다.");
        }
        mailSender.send(mimeMessage);
    }
}
