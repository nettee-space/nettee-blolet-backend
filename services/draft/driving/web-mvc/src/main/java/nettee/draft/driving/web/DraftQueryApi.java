package nettee.draft.driving.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nettee.draft.driving.web.dto.DraftQueryDto.DraftDetailResponse;
import nettee.draft.readmodel.DraftReadModels.DraftDetail;
import nettee.draft.readmodel.DraftReadModels.DraftSummary;
import nettee.draft.application.usecase.DraftReadByStatusesUseCase;
import nettee.draft.application.usecase.DraftReadUseCase;
import nettee.draft.domain.type.DraftStatus;
import nettee.draft.readmodel.DraftReadModels.DraftTitle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

import static nettee.draft.exception.DraftErrorCode.DRAFT_NOT_FOUND;

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
    public DraftDetailResponse getDraft(@PathVariable("draftId") String draftId) {
        DraftDetail draftDetail = draftReadUseCase.getDraft(draftId)
                .orElseThrow(DRAFT_NOT_FOUND::exception);
        return new DraftDetailResponse(draftDetail);
    }

    @Operation(summary = "임시 아티클 목록조회", description = "상태와 함께 임시아티클을 목록조회 합니다(기본 상태는 PENDING(게시전), 추가로 UPDATED(게시후 수정), REMOVED(삭제), PUBLISHED(게시)가 존재함), sortBy(정렬 컬럼)는 title, createAt, updateAt으로 가능하며, ascending(오름차순)은 true/false로 오름차순/내림차순 정렬을 할 수 있습니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @GetMapping
    public List<DraftSummary> getDraftsByStatuses(
            @RequestParam String blogId,
            @RequestParam(defaultValue = "PENDING") Set<DraftStatus> statuses,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "false") Boolean ascending
            ) {
        return draftReadByStatusesUseCase.getDraftsByStatuses(blogId, statuses, sortBy, ascending);
    }

    @Operation(summary = "임시 아티클 제목 조회", description = "ID의 리스트를 받아 임시 아티클의 아이디와 제목을 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @GetMapping("/series-article")
    public List<DraftTitle> getDraftTitlesByIds(
            @RequestParam("ids") List<String> ids
    ) {
        return draftReadUseCase.getDraftTitlesByIds(ids);
    }
}