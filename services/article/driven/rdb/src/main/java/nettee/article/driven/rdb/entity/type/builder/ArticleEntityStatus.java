package nettee.article.driven.rdb.entity.type.builder;

import nettee.article.domain.ArticleStatus;
import nettee.common.marker.TypeSafeMarker.Present;
import nettee.common.status.StatusCodeUtil;
import nettee.common.status.StatusParameters;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static nettee.article.exception.ArticleErrorCode.DEFAULT;
import static nettee.common.status.StatusParameters.GeneralPurposeFeatures.ALL;
import static nettee.common.status.StatusParameters.GeneralPurposeFeatures.SUBITEM_READ;
import static nettee.common.status.StatusParameters.GeneralPurposeFeatures.UPDATE;

public enum ArticleEntityStatus {
    REMOVED(StatusParameters.generate()
            .generalPurposeFeatures(SUBITEM_READ)
            .categoryBits(0b0000_0000_0000_0000)
            .instanceBits(0)
    ),
    PENDING(StatusParameters.generate()
            .generalPurposeFeatures(ALL)
            .categoryBits(0b0000_0000_0000_0001)
            .instanceBits(0)
    ),
    ACTIVE(StatusParameters.generate()
            .generalPurposeFeatures(ALL)
            .categoryBits(0b0000_0000_0000_0010)
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
                .map(ArticleEntityStatus::getCode)
                .collect(Collectors.toSet())
                .size()
                == values().length
                : "ArticleEntityStatus의 모든 code 필드가 고유해야 합니다.";
    }

    ArticleEntityStatus(StatusParameters<Present, Present> statusParameters) {
        this(statusParameters.getAsInt(StatusCodeUtil::getAsInt));
    }

    ArticleEntityStatus(int code) {
        this.code = code;
    }

    public int getCode() { return code; }

    public static ArticleEntityStatus valueOf(ArticleStatus articleStatus) {
        assert Set.of(ArticleStatus.REMOVED, ArticleStatus.PENDING, ArticleStatus.ACTIVE, ArticleStatus.SUSPENDED)
                .containsAll(Arrays.stream(ArticleStatus.values()).collect(Collectors.toSet()))
                : "ArticleStatus 중 일부가 ArticleEntityStatus::valueOf 함수에서 매핑되지 않습니다.";

        return switch (articleStatus) {
            case REMOVED -> REMOVED;
            case PENDING -> PENDING;
            case ACTIVE -> ACTIVE;
            case SUSPENDED -> SUSPENDED;
            default -> throw new Error("ArticleStatus 중 일부가 ArticleEntityStatus::valueOf 함수에서 매핑되지 않습니다.");
        };
    }

    public static ArticleEntityStatus valueOf(int value) {
        int categoryInstanceBits = 0xFFFFFF & value;

        return switch (categoryInstanceBits) {
            case 0b0__000_0000____0000_0000_0000_0000____0000_0000 -> REMOVED;
            case 0b0__000_0000____0000_0000_0000_0001____0000_0000 -> PENDING;
            case 0b0__000_0000____0000_0000_0000_0010____0000_0000 -> ACTIVE;
            case 0b0__000_0000____0000_0000_0000_1000____0000_0000 -> SUSPENDED;
            default -> throw DEFAULT.exception();
        };
    }
}
