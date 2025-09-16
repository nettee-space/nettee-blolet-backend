package nettee.profile.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nettee.blolet.jwt.filter.annotation.AuthUser;
import nettee.blolet.jwt.filter.annotation.AuthorizedUser;
import nettee.profile.readmodel.ProfileCommandModels.ProfileCreateModel;
import nettee.profile.usecase.ProfileCommandUsecase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Profile", description = "프로필 관련 API")
@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileCommandApi {

    private final ProfileCommandUsecase profileCommandUsecase;

    @PostMapping("/create")
    @Operation(
        summary = "프로필 생성",
        description = "사용자의 프로필을 생성합니다."
    )
    public ResponseEntity<String> create(@AuthUser AuthorizedUser authorizedUser,
                                       @RequestBody ProfileCreateModel model) {
        String userId = authorizedUser.userId();
        String profileId = profileCommandUsecase.createProfile(userId, model);
        return ResponseEntity.ok(profileId);
    }
}
