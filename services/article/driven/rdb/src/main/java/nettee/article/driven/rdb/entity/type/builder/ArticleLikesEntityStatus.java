package nettee.article.driven.rdb.entity.type.builder;

import nettee.article.domain.ArticleLikesStatus;
import nettee.article.domain.ArticleStatus;
import nettee.article.driven.rdb.entity.type.builder.TypeSafeMarkers.Present;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static nettee.article.exception.ArticleErrorCode.DEFAULT;

public enum ArticleLikesEntityStatus {
    REMOVED(
        ArticleLikesStatusParameters.builder()
                .canRead(false)
                .classifyingBits(0b0000_0000_0000_0000)
    ),
    ACTIVE(
            ArticleLikesStatusParameters.builder()
                .canRead(true)
                .classifyingBits(0b0000_0000_0000_0010)
    ),
    SUSPENDED(
            ArticleLikesStatusParameters.builder()
                .canRead(true)
                .classifyingBits(0b0000_0000_0000_0100)
    );

    private static final int TLB_PADDING_SIZE = 31;
    private static final int CLASSIFYING_PADDING_SIZE = 15;
    private final int code;

    static {
        assert Arrays.stream(values())
                .map(ArticleLikesEntityStatus::getCode)
                .collect(Collectors.toSet())
                .size()
                == values().length
                : "ArticleEntityStatus의 모든 code 필드가 고유해야 합니다.";
    }

    ArticleLikesEntityStatus(ArticleLikesStatusParameters<Present, Present> articleLikesStatusParameters) {
        this(
                articleLikesStatusParameters.canRead,
                articleLikesStatusParameters.classifyingBits,
                articleLikesStatusParameters.detailBits
        );
    }

    ArticleLikesEntityStatus(boolean canRead, int classifyingBits, int detalBits) {
        this.code = (canRead ? 1 << TLB_PADDING_SIZE : 0)
                | (classifyingBits << CLASSIFYING_PADDING_SIZE)
                | detalBits;
    }

    public int getCode() { return code; }

    public static ArticleLikesEntityStatus valueOf(ArticleLikesStatus articleLikesStatus) {
        assert Set.of(ArticleStatus.DELETED, ArticleStatus.PENDING, ArticleStatus.ACTIVE, ArticleStatus.SUSPENDED)
                .containsAll(Arrays.stream(ArticleStatus.values()).collect(Collectors.toSet()))
                : "ArticleLikesStatus 중 일부가 ArticleLikesEntityStatus::valueOf 함수에서 매핑되지 않습니다.";

        return switch (articleLikesStatus) {
            case REMOVED -> REMOVED;
            case ACTIVE -> ACTIVE;
            case SUSPENDED -> SUSPENDED;
            default -> throw new Error("ArticleLikesStatus 중 일부가 ArticleLikesEntityStatus::valueOf 함수에서 매핑되지 않습니다.");
        };
    }

    public static ArticleLikesEntityStatus valueOf(int value) {
        return switch (value) {
            case 0b0__0000_0000_0000_0000__000_0000_0000_0000 -> REMOVED;
            case 0b1__0000_0000_0000_0010__000_0000_0000_0000 -> ACTIVE;
            case 0b1__0000_0000_0000_0100__000_0000_0000_0000 -> SUSPENDED;
            default -> throw DEFAULT.exception();
        };
    }
}
