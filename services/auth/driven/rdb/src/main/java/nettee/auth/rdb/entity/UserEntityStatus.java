package nettee.auth.rdb.entity;

import static nettee.auth.exception.AuthErrorCode.AUTH_USER_STATUS_INVALID;

import java.util.Map;
import java.util.stream.Stream;
import lombok.Getter;
import nettee.auth.exception.AuthException;

@Getter
public enum UserEntityStatus {
    PENDING(10),        // 회원가입 중, 특히 OAuth 연동은 되었으나 필수 입력 정보가 입력되지 않은 상태
    ACTIVE(20),         // 활성화
    SUSPENDED(30),      // 일시 정지
    PROTECTED(40),      // 계정 보호 (주로 비밀번호를 연속으로 틀릴 때 등)
    REMOVED(99);        // 탈퇴

    private final int code;

    UserEntityStatus(int code) {
        this.code = code;
    }

    // DB에서 조회한 코드를 다시 Enum으로 변환하기 위한 메소드
    public static UserEntityStatus fromCode(int code) {
        return Stream.of(UserEntityStatus.values())
                .filter(status -> status.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new AuthException(
                        AUTH_USER_STATUS_INVALID,
                        () -> Map.of("invalid_userStatusCode", code)));
    }
}
