package nettee.draft.driven.rdb.entity.type;

import nettee.common.marker.TypeSafeMarker.Present;
import nettee.common.status.StatusCodeUtil;
import nettee.common.status.StatusParameters;
import nettee.draft.domain.type.DraftStatus;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static nettee.common.status.StatusParameters.GeneralPurposeFeatures.ALL;
import static nettee.common.status.StatusParameters.GeneralPurposeFeatures.SUBITEM_READ;
import static nettee.common.status.StatusParameters.GeneralPurposeFeatures.UPDATE;
import static nettee.draft.exception.DraftErrorCode.DEFAULT;

public enum DraftEntityStatus {
    REMOVED(StatusParameters.generate()
            .categoryBits(0b0000_0000_0000_0000)
            .instanceBits(0)
    ),
    PENDING(StatusParameters.generate()
            .generalPurposeFeatures(ALL)
            .categoryBits(0b0000_0000_0000_0001)
            .instanceBits(0)
    ),
    PUBLISHED(StatusParameters.generate()
            .generalPurposeFeatures(ALL)
            .categoryBits(0b0000_0000_0000_0010)
            .instanceBits(0)
    ),
    UPDATED(StatusParameters.generate()
            .generalPurposeFeatures(ALL)
            .categoryBits(0b0000_0000_0000_0100)
            .instanceBits(0)
    ),
    SUSPENDED(StatusParameters.generate()
            .generalPurposeFeatures(UPDATE, SUBITEM_READ)
            .categoryBits(0b0000_0000_0000_1000)
            .instanceBits(0)
    ),
    ;

    private final int code;

    static {
        assert Arrays.stream(values())
                .map(DraftEntityStatus::getCode)
                .collect(Collectors.toSet())
                .size()
                == values().length
                : "DraftEntityStatus의 모든 code 필드가 고유해야 합니다.";
    }

    DraftEntityStatus(StatusParameters<Present, Present> statusParameters) {
        this(statusParameters.getAsInt(StatusCodeUtil::getAsInt));
    }

    DraftEntityStatus(int code) { this.code = code; }

    public int getCode() { return code; }

    public static DraftEntityStatus valueOf(DraftStatus draftStatus) {
        assert Set.of(DraftStatus.REMOVED, DraftStatus.PENDING, DraftStatus.UPDATED, DraftStatus.PUBLISHED)
                .containsAll(Arrays.stream(DraftStatus.values()).collect(Collectors.toSet()))
                : "DraftStatus 중 일부가 DraftEntityStatus::valueOf 함수에서 매핑되지 않습니다.";
        return switch (draftStatus) {
            case REMOVED -> REMOVED;
            case PENDING -> PENDING;
            case UPDATED -> UPDATED;
            case PUBLISHED -> PUBLISHED;
            default -> throw new Error("DraftStatus 중 일부가 DraftEntityStatus::valueOf 함수에서 매핑되지 않습니다.");
        };
    }

    public static DraftEntityStatus valueOf(int value) {
        return switch (value) {
            case 0b0__000_0000__0000_0000_0000_0000__0000_0000 -> REMOVED;
            case 0b0__110_1100__0000_0000_0000_0001__0000_0000 -> PENDING;
            case 0b0__110_1100__0000_0000_0000_0010__0000_0000 -> PUBLISHED;
            case 0b0__110_1100__0000_0000_0000_0100__0000_0000 -> UPDATED;
            case 0b0__010_1000__0000_0000_0000_1000__0000_0000 -> SUSPENDED;
            default -> throw DEFAULT.exception();
        };
    }
}
