package nettee.auth.rdb.entity;

import static nettee.auth.exception.AuthErrorCode.AUTH_USER_STATUS_INVALID;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import nettee.auth.exception.AuthException;

@Getter
public enum UserEntityStatus {
    REMOVED(0),         // 탈퇴
    PENDING(10),        // 회원가입 중, 특히 OAuth 연동은 되었으나 필수 입력 정보가 입력되지 않은 상태
    ACTIVE(20),         // 활성화
    SUSPENDED(30),      // 일시 정지
    PROTECTED(40)       // 계정 보호 (주로 비밀번호를 연속으로 틀릴 때 등)
    ;

    private final int code;

    UserEntityStatus(int code) {
        this.code = code;
    }

    // Enum 값을 Map에 미리 저장하여, O(1) 조회를 가능하게 한다.
    private static final Map<Integer, UserEntityStatus> CODE_MAP =
            Collections.unmodifiableMap(
                    Stream.of(UserEntityStatus.values())
                            .collect(Collectors.toMap(
                                    UserEntityStatus::getCode,
                                    status -> status
                            ))
            );

    // DB에서 조회한 코드를 다시 Enum으로 변환하기 위한 메소드
    public static UserEntityStatus fromCode(int code) {
        UserEntityStatus status = CODE_MAP.get(code);
        if (status == null) {
            throw new AuthException(
                    AUTH_USER_STATUS_INVALID,
                    () -> Map.of("invalid userStatusCode", code)
            );
        }
        return status;
    }
}
