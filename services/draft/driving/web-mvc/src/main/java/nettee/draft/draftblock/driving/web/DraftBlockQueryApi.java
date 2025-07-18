package nettee.draft.draftblock.driving.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nettee.draft.draftblock.application.usecase.DraftBlockReadByStatusesUseCase;
import nettee.draft.draftblock.application.usecase.DraftBlockReadUseCase;
import nettee.draft.draftblock.domain.type.DraftBlockStatus;
import nettee.draft.draftblock.driving.web.dto.DraftBlockQueryDto.DraftBlockDetailResponse;
import nettee.draft.draftblock.readmodel.DraftBlockReadModels.DraftBlockSummary;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("draft-blocks")
@RequiredArgsConstructor
@Tag(name = "DraftBlock", description = "DraftBlock API")
public class DraftBlockQueryApi {
    private final DraftBlockReadUseCase draftReadUseCase;
    private final DraftBlockReadByStatusesUseCase draftReadByStatusesUseCase;

    @Operation(summary = "블록 단건조회", description = "블록 ID로 블록을 상세조회 합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @GetMapping("/{draftBlockId}")
    public DraftBlockDetailResponse getDraftBlock(@PathVariable("draftBlockId") long draftBlockId) {
//        DraftBlockQueryModels.DraftBlockDetail draftDetail = draftReadUseCase.getDraftBlock(draftBlockId)
//                .orElseThrow(DRAFT_NOT_FOUND::exception);
//        return new DraftBlockDetailResponse(draftDetail);
        return null;
    }

    @Operation(summary = "블록 목록조회", description = "블록을 상태와 함께 조회합니다. (기본상태는 DRAFT, PUBLISHED이며, 추가로 DELETED가 존재함)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @GetMapping
    public Page<DraftBlockSummary> getDraftBlocksByStatuses(
            @RequestParam(defaultValue = "DRAFT, PUBLISHED") Set<DraftBlockStatus> statuses,
            @RequestParam(defaultValue = "100") int size) {
//        return draftReadByStatusesUseCase.findByStatuses(statuses, size);
        return null;
    }
}