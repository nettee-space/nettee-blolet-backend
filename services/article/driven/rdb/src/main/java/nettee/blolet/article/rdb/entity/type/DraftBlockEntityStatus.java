package nettee.blolet.article.rdb.entity.type;

import nettee.blolet.article.domain.sub.DraftBlockStatus;
import nettee.common.marker.TypeSafeMarker.Present;
import nettee.common.status.StatusCodeUtil;
import nettee.common.status.StatusParameters;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static nettee.blolet.article.exception.DraftBlockErrorCode.DEFAULT;
import static nettee.common.status.StatusParameters.GeneralPurposeFeatures.ALL;
import static nettee.common.status.StatusParameters.GeneralPurposeFeatures.SUBITEM_READ;
import static nettee.common.status.StatusParameters.GeneralPurposeFeatures.UPDATE;

public enum DraftBlockEntityStatus {
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
            .categoryBits(0b0000_0000_0000_0010)
            .instanceBits(0b0001_0000)
    ),
    SUSPENDED(StatusParameters.generate()
            .generalPurposeFeatures(UPDATE, SUBITEM_READ)
            .categoryBits(0b0000_0000_0000_0100)
            .instanceBits(0)
    ),
    ;

    private final int code;

    static {
        assert Arrays.stream(values())
                .map(DraftBlockEntityStatus::getCode)
                .collect(Collectors.toSet())
                .size()
                == values().length
                : "DraftBlockEntityStatus의 모든 code 필드가 고유해야 합니다.";
    }

    DraftBlockEntityStatus(StatusParameters<Present, Present> articleStatusParameters) {
        this(articleStatusParameters.encode(StatusCodeUtil::encode));
    }

    DraftBlockEntityStatus(int code) { this.code = code; }

    public int getCode() { return code; }


    public static DraftBlockEntityStatus valueOf(DraftBlockStatus draftStatus) {
        assert Set.of(DraftBlockStatus.REMOVED, DraftBlockStatus.PENDING, DraftBlockStatus.PUBLISHED)
                .containsAll(Arrays.stream(DraftBlockStatus.values()).collect(Collectors.toSet()))
                : "DraftBlockStatus 중 일부가 DraftBlockEntityStatus::valueOf 함수에서 매핑되지 않습니다.";

        return switch (draftStatus) {
            case REMOVED -> REMOVED;
            case PENDING -> PENDING;
            case UPDATED -> UPDATED;
            case PUBLISHED -> PUBLISHED;
            case SUSPENDED -> SUSPENDED;
            default -> throw new Error("DraftBlockStatus 중 일부가 DraftBlockEntityStatus::valueOf 함수에서 매핑되지 않습니다.");
        };
    }

    public static DraftBlockEntityStatus valueOf(int value) {
        int categoryInstanceBits = 0xFFFFFF & value;

        return switch (categoryInstanceBits) {
            case 0b0__000_0000____0000_0000_0000_0000____0000_0000 -> REMOVED;
            case 0b0__000_0000____0000_0000_0000_0001____0000_0000 -> PENDING;
            case 0b0__000_0000____0000_0000_0000_0010____0000_0000 -> PUBLISHED;
            case 0b0__000_0000____0000_0000_0000_0010____0001_0000 -> UPDATED;
            case 0b0__000_0000____0000_0000_0000_0100____0000_0000 -> SUSPENDED;
            default -> throw DEFAULT.exception();
        };
    }
}