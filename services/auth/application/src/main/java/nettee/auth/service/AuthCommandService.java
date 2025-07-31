package nettee.auth.service;


import static nettee.auth.exception.AuthErrorCode.AUTH_ACCOUNT_ALREADY_EXIST;
import static nettee.auth.exception.AuthErrorCode.AUTH_ACCOUNT_NOT_FOUND;
import static nettee.auth.exception.AuthErrorCode.AUTH_PASSWORD_MISMATCHED;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import nettee.auth.domain.User;
import nettee.auth.domain.UserStatus;
import nettee.auth.exception.AuthException;
import nettee.auth.port.AuthCommandRepositoryPort;
import nettee.auth.port.AuthQueryRepositoryPort;
import nettee.auth.port.AuthRedisPort;
import nettee.auth.usecase.AuthSignUsecase;
import nettee.blolet.auth.readmodel.AuthCommandModels.LoginTokenModel;
import nettee.blolet.auth.readmodel.AuthCommandModels.SignUpRequestModel;
import nettee.jwt.JwtIssuer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthCommandService implements AuthSignUsecase {

    private final AuthCommandRepositoryPort authCommandRepositoryPort;
    private final AuthQueryRepositoryPort authQueryRepositoryPort;
    private final AuthRedisPort authRedisPort;

    private final PasswordEncoder passwordEncoder;
    private final JwtIssuer jwtIssuer;

    private static final int ACCESS_TOKEN_EXPIRATION = 600; // accessToken 유효 기간

    @Override
    public LoginTokenModel signUp(SignUpRequestModel model) {
        /**
         * TODO: 회원가입 이메일 인증을 정상적으로 수행했는지 검증
         * 사용자 이메일 인증이 완료되었음을 확인하는 토큰을 받을 수 있다.
         */

        /**
         * 입력값에 대한 검증은 웹 계층에서 수행, 비밀번호 유효성 검증
         * 웹 계층에서 검증하는 이유는 다음과 같습니다.
         *   서비스 로직을 실행하기 앞서 차단할 수 있다.
         *   서비스 로직에서 입력값 검증을 수행하지 않아도 되므로, 코드를 간결히 유지할 수 있다.
         *   spring 어노테이션을 쉽게 활용할 수 있다. (@Valid, @Pattern, @NotBlank 등)
         */

        // 1. 필수 약관 동의 여부 확인
        User.validateAgreed(model.agreedTerms(), model.agreedPrivacy());

        // 2. login ID 중복 체크
        boolean exists = authQueryRepositoryPort.existsByLoginId(model.loginId());
        if (exists) {
            throw new AuthException(AUTH_ACCOUNT_ALREADY_EXIST);
        }

        // 3. password 암호화 (Argon2id, 가변 솔트 사용)
        String encodedPassword = passwordEncoder.encode(model.password());

        // 4. user 도메인 객체 생성
        User user = User.builder()
                .loginId(model.loginId())
                .encodedPassword(encodedPassword)
                .nickname(model.nickname())
                .email(model.email())
                .status(UserStatus.ACTIVE) // 기본 상태는 ACTIVE
                .build();

        // 5. user 저장
        authCommandRepositoryPort.save(user);

        // 6. 회원가입 성공 시, 자동 로그인 처리를 위해 accessToken & refreshToken 발급
        return generateLoginToken(user);
    }

    @Override
    public LoginTokenModel signIn(String loginId, String rawPassword) throws AuthException {
        // 1. login ID로 사용자 조회
        User userEntity = authQueryRepositoryPort.findByLoginId(loginId)
                .orElseThrow(() -> new AuthException(AUTH_ACCOUNT_NOT_FOUND));

        // 2. 비밀번호 검증
        if (!passwordEncoder.matches(rawPassword, userEntity.getEncodedPassword())) {
            throw new AuthException(AUTH_PASSWORD_MISMATCHED);
        }

        // 3. 사용자 인증 성공 시, accessToken & refreshToken 발급
        return generateLoginToken(userEntity);
    }

    /**
     * 로그인 성공 시, accessToken과 refreshToken을 발급합니다.
     * accessToken은 JWT 형식으로 발급되며, refreshToken은 암호화된 형태로 Redis에 저장합니다.
     */
    private LoginTokenModel generateLoginToken(User userEntity) {
        // accessToken 발급
        Map<String, Object> claims = Map.of(
                "userId", userEntity.getId()
        );
        String accessToken = jwtIssuer.issue(userEntity.getId(), claims, ACCESS_TOKEN_EXPIRATION);

        // refreshToken 발급
        String refreshToken = generateSecureRandom();
        String hashedRefreshToken = hashSha256(refreshToken);
        String hashedRefreshTokenKey = userEntity.getId() + ":" + hashedRefreshToken;

        // refreshToken 저장
        authRedisPort.saveRefreshToken(hashedRefreshTokenKey, hashedRefreshToken);

        return LoginTokenModel.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * SHA-256 해시 함수를 사용하여 문자열을 해싱합니다.
     */
    private String hashSha256(String token) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(token.getBytes(StandardCharsets.UTF_8));

            return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 암호학적으로 안전한 난수를 생성합니다. (uuid보다 안전)
     */
    private String generateSecureRandom() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }
}
