package nettee.auth.domain;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String loginId;
    private String password;
    private String nickname;
    private String email;
    private UserStatus status;

    private Integer loginRetryCount;
    private Instant lockedUntil;

    private boolean agreedTerms;   // 이용약관 동의
    private boolean agreedPrivacy; // 개인정보처리방침 동의
    private boolean agreedEmailAd; // 이메일 광고수신 동의

    // 회원가입 필수 약관 동의 여부 확인
    public void validateAgreed() {
        if (!agreedTerms || !agreedPrivacy) {
            throw new IllegalArgumentException("필수 약관에 동의하지 않았습니다.");
        }
    }

    // 비밀번호 해싱
    public void encodePasswordWithRandomSalt() {
        // Spring Security 5.8부터 권장되는 기본 파라미터를 사용하여 Argon2PasswordEncoder 인스턴스를 생성한다.
        // saltLength=16, hashLength=32, parallelism=1, memory=16384 (16MiB), iterations=2
        Argon2PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();

        // 내부적으로 안전한 가변 솔트를 생성하고,
        // 이를 사용하여 비밀번호를 해싱한 후, 최종 결과 해싱 문자열에 가변 솔트를 포함하여 반환한다.
        encoder.encode(this.password);
    }
}
