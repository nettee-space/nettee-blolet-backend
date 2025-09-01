package nettee.blolet.article.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nettee.blolet.article.application.usecase.DraftValidationResponseUseCase;
import nettee.common.validation.model.ValidationResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Draft", description = "Draft API")
public class DraftCustomPathQueryApi {
    private final DraftValidationResponseUseCase validationResponseUseCase;

    @GetMapping("/api/validations/draft")
    @Operation(summary = "임시 글의 유효성 정책", description = "context를 입력받아 드래프트 유효성 정책을 응답합니다.")
    @Parameter(name = "context", description = "`create` 또는 `patch`를 지원합니다.", example = "create")
    public ValidationResponseModel responseValidation(
            @RequestParam("context") String context
    ) {
        return validationResponseUseCase.responseValidation(context);
    }
}
