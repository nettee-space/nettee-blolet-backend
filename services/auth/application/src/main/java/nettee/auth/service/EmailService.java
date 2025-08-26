package nettee.auth.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.text.MessageFormat;
import lombok.RequiredArgsConstructor;
import nettee.auth.port.MailSender;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService implements MailSender {

    private final JavaMailSender mailSender;

    // MessageFormat 객체를 한 번만 생성하고, ThreadLocal을 사용하여 각 스레드에서 안전하게 재사용할 수 있도록 한다.
    private final ThreadLocal<MessageFormat> otpHtmlFormat =
            ThreadLocal.withInitial(() -> new MessageFormat("""
                    <div>
                        <h2>이메일 인증</h2>
                        <p>아래의 인증 번호를 입력해 주세요.</p>
                        <p><b>{0}</b></p>
                    </div>
                    """));

    private final ThreadLocal<MessageFormat> passwordResetHtmlFormat =
            ThreadLocal.withInitial(() -> new MessageFormat("""
                    <div>
                        <h2>비밀번호 재설정</h2>
                        <p>아래의 링크를 클릭하여 비밀번호를 재설정 해주세요.</p>
                    </div>
                    """));

    @Override
    public void sendOtp(String email, String otp) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        MessageFormat messageFormat = otpHtmlFormat.get();
        String htmlContent = messageFormat.format(new Object[]{otp});

        try {
            helper.setTo(email);
            helper.setSubject("[Blolet] 이메일 인증");
            helper.setText(htmlContent, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("이메일 인증 메시지 생성 중 오류가 발생했습니다.", e);
        } catch (MailException e) {
            throw new RuntimeException("이메일 인증 메일 전송 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public void sendPasswordReset(String email, String passwordResetUrl) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        MessageFormat messageFormat = passwordResetHtmlFormat.get();
        String htmlContent = messageFormat.format(new Object[]{passwordResetUrl});

        try {
            helper.setTo(email);
            helper.setSubject("[Blolet] 비밀번호 재설정");
            helper.setText(htmlContent, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("비밀번호 재설정 메시지 생성 중 오류가 발생했습니다.", e);
        } catch (MailException e) {
            throw new RuntimeException("비밀번호 재설정 메일 전송 중 오류가 발생했습니다.", e);
        }
    }
}
