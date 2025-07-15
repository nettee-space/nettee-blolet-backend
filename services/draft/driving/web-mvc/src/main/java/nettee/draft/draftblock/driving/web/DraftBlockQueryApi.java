package nettee.draft.draftblock.driving.web;

import lombok.RequiredArgsConstructor;
import nettee.draft.draftblock.application.usecase.DraftBlockReadByStatusesUseCase;
import nettee.draft.draftblock.application.usecase.DraftBlockReadUseCase;
import nettee.draft.draftblock.domain.type.DraftBlockStatus;
import nettee.draft.draftblock.driving.web.dto.DraftBlockQueryDto.DraftBlockDetailResponse;
import nettee.draft.draftblock.readmodel.DraftBlockQueryModels;
import nettee.draft.draftblock.readmodel.DraftBlockQueryModels.DraftBlockSummary;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static nettee.draft.draftblock.exception.DraftBlockQueryErrorCode.DRAFT_NOT_FOUND;

@RestController
@RequestMapping("draftBlocks")
@RequiredArgsConstructor
public class DraftBlockQueryApi {
    private final DraftBlockReadUseCase draftReadUseCase;
    private final DraftBlockReadByStatusesUseCase draftReadByStatusesUseCase;

    @GetMapping("/{draftBlockId}")
    public DraftBlockDetailResponse getDraftBlock(@PathVariable("draftBlockId") long draftBlockId) {
        DraftBlockQueryModels.DraftBlockDetail draftDetail = draftReadUseCase.getDraftBlock(draftBlockId)
                .orElseThrow(DRAFT_NOT_FOUND::exception);
        return new DraftBlockDetailResponse(draftDetail);
    }

    @GetMapping
    public Page<DraftBlockSummary> getDraftBlocksByStatuses(
            @RequestParam(defaultValue = "DRAFT, PUBLISHED") Set<DraftBlockStatus> statuses,
            @RequestParam(defaultValue = "100") int size) {
        return draftReadByStatusesUseCase.findByStatuses(statuses, size);
    }
}