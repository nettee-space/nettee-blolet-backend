package nettee.draft.driving.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nettee.draft.driving.web.dto.DraftQueryDto.DraftDetailResponse;
import nettee.draft.readmodel.DraftReadModels.DraftSummary;
import nettee.draft.application.usecase.DraftReadByStatusesUseCase;
import nettee.draft.application.usecase.DraftReadUseCase;
import nettee.draft.domain.type.DraftStatus;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;

@RestController
@RequestMapping("drafts")
@RequiredArgsConstructor
@Tag(name = "Draft", description = "Draft API")
public class DraftQueryApi {
    private final DraftReadUseCase draftReadUseCase;
    private final DraftReadByStatusesUseCase draftReadByStatusesUseCase;

    @Operation(summary = "임시 아티클 단건조회", description = "임시아티클을 상세조회 합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @GetMapping("/{draftId}")
    public DraftDetailResponse getDraft(@PathVariable("draftId") long draftId) {
//        DraftQueryModels.DraftDetail draftDetail = draftReadUseCase.getDraft(draftId)
//                .orElseThrow(DRAFT_NOT_FOUND::exception);
//        return new DraftDetailResponse(draftDetail);
        return null;
    }

    @Operation(summary = "임시 아티클 목록조회", description = "상태와 함께 임시아티클을 목록조회 합니다(기본 상태는 DRAFT이며, 추가로 PENDING, DELETED, DONE이 존재함)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @GetMapping
    public Page<DraftSummary> getDraftsByStatuses(
            @RequestParam(defaultValue = "DRAFT") Set<DraftStatus> statuses,
            @RequestParam(defaultValue = "100") int size) {
//        return draftReadByStatusesUseCase.findByStatuses(statuses, size);
        return null;
    }
}