package nettee.article.driven.rdb.entity.type.builder;

import nettee.blolet.article.domain.sub.ArticleLikesStatus;
import nettee.blolet.article.domain.sub.ArticleStatus;
import nettee.common.marker.TypeSafeMarker.Present;
import nettee.common.status.StatusCodeUtil;
import nettee.common.status.StatusParameters;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static nettee.blolet.article.exception.ArticleErrorCode.DEFAULT;
import static nettee.common.status.StatusParameters.GeneralPurposeFeatures.ALL;
import static nettee.common.status.StatusParameters.GeneralPurposeFeatures.SUBITEM_READ;

public enum ArticleLikesEntityStatus {
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
            .categoryBits(0b0000_0000_0000_0100)
            .instanceBits(0)
    );

    private final int code;

    static {
        assert Arrays.stream(values())
                .map(ArticleLikesEntityStatus::getCode)
                .collect(Collectors.toSet())
                .size()
                == values().length
                : "ArticleEntityStatus의 모든 code 필드가 고유해야 합니다.";
    }

    ArticleLikesEntityStatus(StatusParameters<Present, Present> statusParameters) {
        this(statusParameters.encode(StatusCodeUtil::encode));
    }

    ArticleLikesEntityStatus(int code) {
        this.code = code;
    }

    public int getCode() { return code; }

    public static ArticleLikesEntityStatus valueOf(ArticleLikesStatus articleLikesStatus) {
        assert Set.of(ArticleStatus.REMOVED, ArticleStatus.PENDING, ArticleStatus.ACTIVE, ArticleStatus.SUSPENDED)
                .containsAll(Arrays.stream(ArticleStatus.values()).collect(Collectors.toSet()))
                : "ArticleLikesStatus 중 일부가 ArticleLikesEntityStatus::valueOf 함수에서 매핑되지 않습니다.";

        return switch (articleLikesStatus) {
            case REMOVED -> REMOVED;
            case PENDING -> PENDING;
            case ACTIVE -> ACTIVE;
            case SUSPENDED -> SUSPENDED;
            default -> throw new Error("ArticleLikesStatus 중 일부가 ArticleLikesEntityStatus::valueOf 함수에서 매핑되지 않습니다.");
        };
    }

    public static ArticleLikesEntityStatus valueOf(int value) {
        int categoryInstanceBits = 0xFFFFFF & value;

        return switch (categoryInstanceBits) {
            case 0b0__000_0000____0000_0000_0000_0000____0000_0000 -> REMOVED;
            case 0b0__000_0000____0000_0000_0000_0001____0000_0000 -> PENDING;
            case 0b0__000_0000____0000_0000_0000_0010____0000_0000 -> ACTIVE;
            case 0b0__000_0000____0000_0000_0000_0100____0000_0000 -> SUSPENDED;
            default -> throw DEFAULT.exception();
        };
    }
}
