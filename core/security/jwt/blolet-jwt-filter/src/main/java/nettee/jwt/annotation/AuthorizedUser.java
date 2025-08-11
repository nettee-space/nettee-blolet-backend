package nettee.jwt.annotation;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * JWT 토큰 내의 인증된 사용자 정보
 */
@Getter
@Builder
@ToString
public class AuthorizedUser {
    
    /**
     * 사용자 ID (JWT subject)
     */
    private final String userId;
    
    /**
     * 사용자 권한 목록
     */
    private final List<String> roles;
    
    /**
     * 사용자 프로필 ID 목록
     */
    private final List<String> profileIds;


    // 사용자가 인증되었는지 확인
    public boolean hasUserId() {
        return userId != null && !userId.isEmpty();
    }

    // 사용자가 특정 역할을 가지고 있는지 확인
    public boolean hasRoles(String role) {
        if (roles == null || roles.isEmpty()){
            return false;
        }
        return roles.contains(role);
    }
    
    // 사용자가 특정 프로필 ID를 가지고 있는지 확인
    public boolean hasProfileId(String profileId) {
        if (profileIds == null || profileIds.isEmpty()) {
            return false;
        }
        return profileIds.contains(profileId);
    }
}
