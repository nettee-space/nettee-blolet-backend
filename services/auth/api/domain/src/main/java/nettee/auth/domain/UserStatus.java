package nettee.auth.domain;

public enum UserStatus {
    PENDING,        // 회원가입 중, 특히 OAuth 연동은 되었으나 필수 입력 정보가 입력되지 않은 상태
    ACTIVE,         // 활성화
    SUSPENDED,      // 일시 정지
    PROTECTED,      // 계정 보호 (주로 비밀번호를 연속으로 틀릴 때 등)
    REMOVED,        // 탈퇴
}
