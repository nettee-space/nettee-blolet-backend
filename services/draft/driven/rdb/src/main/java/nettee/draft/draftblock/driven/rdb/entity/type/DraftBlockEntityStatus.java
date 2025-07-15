package nettee.draft.draftblock.driven.rdb.entity.type;

import nettee.common.marker.TypeSafeMarker.Present;
import nettee.common.status.StatusCodeUtil;
import nettee.common.status.StatusParameters;
import nettee.common.status.StatusParameters.GeneralPurposeFeatures;
import nettee.draft.draftblock.domain.type.DraftBlockStatus;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static nettee.draft.draftblock.exception.DraftBlockCommandErrorCode.DEFAULT;

public enum DraftBlockEntityStatus {
    DELETED(
            StatusParameters.generate()
                    .generalPurposeFeatures(
                            GeneralPurposeFeatures.READ,
                            GeneralPurposeFeatures.SUBITEM_READ
                    )
                    .categoryBits(0b0000_0000_0000_0000)
                    .instanceBits(0)
    ),
    DRAFT(
            StatusParameters.generate()
                    .generalPurposeFeatures(GeneralPurposeFeatures.ALL)
                    .categoryBits(0b0000_0000_0000_0001)
                    .instanceBits(0)
    ),
    PUBLISHED(
            StatusParameters.generate()
                    .generalPurposeFeatures(GeneralPurposeFeatures.ALL)
                    .categoryBits(0b0000_0000_0000_0010)
                    .instanceBits(0)
    );

    private static final int TLB_PADDING_SIZE = 31;
    private static final int CLASSIFYING_PADDING_SIZE = 15;
    private final int code;

    static {
        assert Arrays.stream(values())
                .map(DraftBlockEntityStatus::getCode)
                .collect(Collectors.toSet())
                .size()
                == values().length
                : "DraftEntityStatus의 모든 code 필드가 고유해야 합니다.";
    }

    DraftBlockEntityStatus(StatusParameters<Present, Present> articleStatusParameters) {
        this(
                StatusCodeUtil.getAsInt(articleStatusParameters)
        );
    }

    DraftBlockEntityStatus(int code) { this.code = code; }

    public int getCode() { return code; }


    public static DraftBlockEntityStatus valueOf(DraftBlockStatus draftStatus) {
        assert Set.of(DraftBlockStatus.DELETED, DraftBlockStatus.PUBLISHED, DraftBlockStatus.DRAFT)
                .containsAll(Arrays.stream(DraftBlockStatus.values()).collect(Collectors.toSet()))
                : "DraftBlockStatus 중 일부가 DraftEntityStatus::valueOf 함수에서 매핑되지 않습니다.";

        return switch (draftStatus) {
            case DELETED -> DELETED;
            case PUBLISHED -> PUBLISHED;
            case DRAFT -> DRAFT;
            default -> throw new Error("DraftBlockStatus 중 일부가 DraftEntityStatus::valueOf 함수에서 매핑되지 않습니다.");
        };
    }

    public static DraftBlockEntityStatus valueOf(int value) {
        return switch (value) {
            case 0b0__0000_0000_0000_0000__000_0000_0000_0000 -> DELETED;
            case 0b0__0000_0000_0000_0001__000_0000_0000_0000 -> DRAFT;
            case 0b1__0000_0000_0000_0010__000_0000_0000_0000 -> PUBLISHED;
            default -> throw DEFAULT.exception();
        };
    }
}
