package nettee.profile.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import nettee.profile.readmodel.ProfileCommandModels.ProfileCreateModel;
import nettee.profile.usecase.ProfileCommandUsecase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/profile")
@RequiredArgsConstructor
public class ProfileInternalCommandApi {

    private final ProfileCommandUsecase profileCommandUsecase;

    @PostMapping("/create")
    @Operation(
        summary = "프로필 생성",
        description = "사용자의 프로필을 생성합니다."
    )
    public ResponseEntity<String> create(@RequestBody ProfileCreateModel model) {
        String profileId = profileCommandUsecase.createProfile(model);
        return ResponseEntity.ok(profileId);
    }
}
