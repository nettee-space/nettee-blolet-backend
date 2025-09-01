package nettee.blole.article.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nettee.blole.article.web.dto.DraftBlockQueryDto.DraftBlockDetailResponse;
import nettee.blolet.article.application.usecase.DraftBlockReadByStatusUseCase;
import nettee.blolet.article.application.usecase.DraftBlockReadUseCase;
import nettee.blolet.article.domain.sub.DraftBlockStatus;
import nettee.blolet.article.readmodel.DraftBlockReadModels.DraftBlockDetail;
import nettee.blolet.article.readmodel.DraftBlockReadModels.DraftBlockSummary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static nettee.blolet.article.exception.DraftBlockErrorCode.DRAFT_BLOCK_NOT_FOUND;

@RestController
@RequestMapping("draft-blocks")
@RequiredArgsConstructor
@Tag(name = "DraftBlock", description = "DraftBlock API")
public class DraftBlockQueryApi {
    private final DraftBlockReadUseCase draftReadUseCase;
    private final DraftBlockReadByStatusUseCase draftReadByStatusUseCase;

    @Operation(summary = "블록 단건조회", description = "블록 ID로 블록을 상세조회 합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @GetMapping("/{draftBlockId}")
    public DraftBlockDetailResponse getDraftBlock(@PathVariable("draftBlockId") String draftBlockId) {
        DraftBlockDetail draftDetail = draftReadUseCase.getDraftBlock(draftBlockId)
                .orElseThrow(DRAFT_BLOCK_NOT_FOUND::exception);
        return new DraftBlockDetailResponse(draftDetail);
    }

    @Operation(summary = "블록 목록조회", description = "블록을 상태와 함께 조회합니다. (기본상태는 DRAFT, PUBLISHED이며, 추가로 DELETED가 존재함)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @GetMapping
    public List<DraftBlockSummary> getDraftBlocks(
            @RequestParam String articleId,
            @RequestParam(defaultValue = "PUBLISHED") DraftBlockStatus status
            ) {
        return draftReadByStatusUseCase.getDraftBlocksByStatus(articleId, status);
    }
}