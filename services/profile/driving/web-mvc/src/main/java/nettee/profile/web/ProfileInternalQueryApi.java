package nettee.profile.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import nettee.profile.usecase.ProfileQueryUsecase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/profile")
@RequiredArgsConstructor
public class ProfileInternalQueryApi {

    private final ProfileQueryUsecase profileQueryUsecase;

    /**
     * 로그인 및 jwt 재발행 시, profileId를 클레임에 담기 위한 API
     */
    @GetMapping("/{userId}")
    @Operation(
        summary = "사용자 프로필 ID 조회",
        description = "사용자의 프로필 ID를 조회합니다."
    )
    public ResponseEntity<String> findProfileId(@PathVariable("userId") String userId) {
        String profileId = profileQueryUsecase.findProfileId(userId);
        return ResponseEntity.ok(profileId);
    }
}
