package nettee.draft.driven.rdb.entity.type;

import nettee.draft.domain.type.DraftStatus;
import nettee.common.marker.TypeSafeMarker.Present;
import nettee.common.status.StatusCodeUtil;
import nettee.common.status.StatusParameters;
import nettee.common.status.StatusParameters.GeneralPurposeFeatures;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static nettee.draft.exception.DraftCommandErrorCode.DEFAULT;

public enum DraftEntityStatus {
    DELETED(
            StatusParameters.generate()
                    .generalPurposeFeatures(
                            GeneralPurposeFeatures.READ,
                            GeneralPurposeFeatures.SUBITEM_READ
                    )
                    .categoryBits(0b0000_0000_0000_0000)
                    .instanceBits(0)
    ),
    PENDING(
            StatusParameters.generate()
                    .categoryBits(0b0000_0000_0000_0001)
                    .instanceBits(0)
    ),
    DRAFT(
            StatusParameters.generate()
                    .generalPurposeFeatures(GeneralPurposeFeatures.ALL)
                    .categoryBits(0b0000_0000_0000_0010)
                    .instanceBits(0)
    ),
    DONE(
            StatusParameters.generate()
                    .generalPurposeFeatures(
                            GeneralPurposeFeatures.READ,
                            GeneralPurposeFeatures.SUBITEM_READ
                    )
                    .categoryBits(0b0000_0000_0000_0100)
                    .instanceBits(0)
    );

    private static final int TLB_PADDING_SIZE = 31;
    private static final int CLASSIFYING_PADDING_SIZE = 15;
    private final int code;

    static {
        assert Arrays.stream(values())
                .map(DraftEntityStatus::getCode)
                .collect(Collectors.toSet())
                .size()
                == values().length
                : "DraftEntityStatus의 모든 code 필드가 고유해야 합니다.";
    }

    DraftEntityStatus(StatusParameters<Present, Present> articleStatusParameters) {
        this(
                StatusCodeUtil.getAsInt(articleStatusParameters)
        );
    }

    DraftEntityStatus(int code) { this.code = code; }

    public int getCode() { return code; }


    public static DraftEntityStatus valueOf(DraftStatus draftStatus) {
        assert Set.of(DraftStatus.DELETED, DraftStatus.PENDING, DraftStatus.DRAFT, DraftStatus.DONE)
                .containsAll(Arrays.stream(DraftStatus.values()).collect(Collectors.toSet()))
                : "DraftStatus 중 일부가 DraftEntityStatus::valueOf 함수에서 매핑되지 않습니다.";
        return switch (draftStatus) {
            case DELETED -> DELETED;
            case PENDING -> PENDING;
            case DRAFT -> DRAFT;
            case DONE -> DONE;
            default -> throw new Error("DraftStatus 중 일부가 DraftEntityStatus::valueOf 함수에서 매핑되지 않습니다.");
        };
    }

    public static DraftEntityStatus valueOf(int value) {
        return switch (value) {
            case 0b0__100_1000_0000_0000_0000_0000_0000_0000 -> DELETED;
            case 0b0__000_0000_0000_0000_0000_0001_0000_0000 -> PENDING;
            case 0b0__110_1100_0000_0000_0000_0010_0000_0000 -> DRAFT;
            case 0b0__100_1000_0000_0000_0000_0100_0000_0000 -> DONE;
            default -> {
                throw DEFAULT.exception();}
        };
    }
}
