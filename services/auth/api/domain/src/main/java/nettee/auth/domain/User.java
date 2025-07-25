package nettee.auth.domain;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String loginId;
    private String encodedPassword;
    private String nickname;
    private String email;
    private UserStatus status;

    private Integer loginRetryCount;
    private Instant lockedUntil;

    // 회원가입 필수 약관 동의 여부 확인
    public static void validateAgreed(boolean agreedTerms, boolean agreedPrivacy) {
        if (!agreedTerms || !agreedPrivacy) {
            throw new IllegalArgumentException("필수 약관에 동의하지 않았습니다.");
        }
    }
}
