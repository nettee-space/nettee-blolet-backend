package nettee.article.driven.rdb.entity.type.builder;

import nettee.article.driven.rdb.entity.type.builder.TypeSafeMarkers.Missing;
import nettee.article.driven.rdb.entity.type.builder.TypeSafeMarkers.Present;

public class ArticleLikesStatusParameters<
        HAS_CAN_READ extends TypeSafeMarkers,
        HAS_CLASSIFYING_BITS extends TypeSafeMarkers
> {
    boolean canRead;
    Integer classifyingBits;
    int detailBits;
    private ArticleLikesStatusParameters() {}

    public static ArticleLikesStatusParameters<Missing, Missing> builder() { return new ArticleLikesStatusParameters<>(); }

    public static ArticleLikesStatusParameters<Missing, Missing> generate() {
        return new ArticleLikesStatusParameters<>();
    }

    @SuppressWarnings("unchecked")
    public ArticleLikesStatusParameters<HAS_CAN_READ, Present> classifyingBits(Integer classifyingBits) {
        this.classifyingBits = classifyingBits;
        return (ArticleLikesStatusParameters<HAS_CAN_READ, Present>) this;
    }

    @SuppressWarnings("unchecked")
    public ArticleLikesStatusParameters<Present, HAS_CLASSIFYING_BITS> canRead(boolean canRead) {
        this.canRead = canRead;
        return (ArticleLikesStatusParameters<Present, HAS_CLASSIFYING_BITS>) this;
    }

    public ArticleLikesStatusParameters<HAS_CAN_READ, HAS_CLASSIFYING_BITS> detailBits(int detailBits) {
        this.detailBits = detailBits;
        return this;
    }

}
