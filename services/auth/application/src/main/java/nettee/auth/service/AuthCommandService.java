package nettee.auth.service;

import static nettee.auth.exception.AuthErrorCode.AUTH_ACCOUNT_ALREADY_EXIST;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import nettee.auth.domain.User;
import nettee.auth.exception.AuthException;
import nettee.auth.port.AuthCommandRepositoryPort;
import nettee.auth.port.AuthQueryRepositoryPort;
import nettee.auth.port.AuthRedisPort;
import nettee.auth.usecase.AuthSignUsecase;
import nettee.series.readmodel.AuthCommandModels.LoginToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthCommandService implements AuthSignUsecase {

    private final AuthCommandRepositoryPort authCommandRepositoryPort;
    private final AuthQueryRepositoryPort authQueryRepositoryPort;
    private final AuthRedisPort authRedisPort;

    @Override
    public void signUp(User user) {
        /**
         * TODO: 회원가입 이메일 인증을 정상적으로 수행했는지 검증
         * 사용자 이메일 인증이 완료되었음을 확인하는 토큰을 받을 수 있다.
         */

        /**
         * TODO: 입력값에 대한 검증은 웹 계층에서 수행, 비밀번호 유효성 검증
         * 다음과 같은 이유로 웹 계층에서 검증을 한다.
         *   서비스 로직을 실행하기 앞서 차단할 수 있다.
         *   서비스 로직에서 입력값 검증을 수행하지 않아도 되므로, 코드를 간결히 유지할 수 있다.
         *   spring 어노테이션을 쉽게 활용할 수 있다. (@Valid, @Pattern, @NotBlank 등)
         */
        // 1. login ID 중복 체크
        authQueryRepositoryPort.findByLoginId(user.getLoginId())
                .ifPresent(existingUser -> {
                    throw new AuthException(AUTH_ACCOUNT_ALREADY_EXIST);
                });

        // 2. 필수 약관 동의 여부 확인
        user.validateAgreed();

        // 3. password 암호화 (Argon2id, 가변 솔트 사용)
        user.encodePasswordWithRandomSalt();

        // 4. user 저장
        authCommandRepositoryPort.save(user);
    }
}
