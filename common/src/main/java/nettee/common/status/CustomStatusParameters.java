package nettee.common.status;

import nettee.common.marker.TypeSafeMarker;
import nettee.common.marker.TypeSafeMarker.Missing;
import nettee.common.marker.TypeSafeMarker.Present;

import java.util.Collection;

import static nettee.common.status.exception.StatusCodeErrorCode.CATEGORY_BITS_OVERFLOW;
import static nettee.common.status.exception.StatusCodeErrorCode.INSTANCE_DETAIL_BITS_OVERFLOW;
import static nettee.common.status.exception.StatusCodeErrorCode.SYS_INFO_OVERFLOW;

/**
 * {@code long} 타입 이내에서 GP bits, system information bits, category bits, instance detail bits 구간별 사이즈를 커스텀하여
 * 사용할 수 있도록 일반화한 클래스입니다.
 *
 * @param <C> Category bits 입력을 필수로 합니다. (컴파일타임 체크)
 * @param <I> Instance detail bits 입력을 필수로 합니다. (컴파일타임 체크)
 */
public class CustomStatusParameters<
        C extends TypeSafeMarker,
        I extends TypeSafeMarker
> {
    private final CustomStatusParametersSupplier supplier;

    private long generalPurposeBits;
    private long systemInfoBits;

    private long categoryBits;
    private long instanceBits;

    CustomStatusParameters(CustomStatusParametersSupplier supplier) {
        this.supplier = supplier;
    }

    public static CustomStatusParameters<Missing, Missing> generateWith(CustomStatusParametersSupplier supplier) {
        return new CustomStatusParameters<>(supplier);
    }

    public CustomStatusParameters<C, I> generalPurposeFeatures(
            Collection<String> extendedFeatures,
            LongGeneralPurposeFeatures... features
    ) {
        generalPurposeBits = supplier.features().getValueOf(extendedFeatures, features);
        return this;
    }

    public CustomStatusParameters<C, I> generalPurposeFeatures(LongGeneralPurposeFeatures... features) {
        generalPurposeBits = supplier.features().getValueOf(features);
        return this;
    }

    public CustomStatusParameters<C, I> systemInfoBits(long systemInfoBits) {
        long sysInfoMax = (1L << supplier.systemInfoBitSize()) - 1;
        if (systemInfoBits > sysInfoMax) {
            throw SYS_INFO_OVERFLOW.exception();
        }
        this.systemInfoBits = systemInfoBits;
        return this;
    }

    public CustomStatusParameters<Present, I> categoryBits(long categoryBits) {
        long categoryMax = (1L << supplier.categoryBitSize()) - 1;
        if (categoryBits > categoryMax) {
            throw CATEGORY_BITS_OVERFLOW.exception();
        }
        this.categoryBits = categoryBits;
        @SuppressWarnings("unchecked")
        var instance = (CustomStatusParameters<Present, I>) this;
        return instance;
    }

    public CustomStatusParameters<C, Present> instanceBits(long instanceBits) {
        long instanceDetailMax = (1L << supplier.instanceDetailBitSize()) - 1;
        if (instanceBits > instanceDetailMax) {
            throw INSTANCE_DETAIL_BITS_OVERFLOW.exception();
        }
        this.instanceBits = instanceBits;
        @SuppressWarnings("unchecked")
        var instance = (CustomStatusParameters<C, Present>) this;
        return instance;
    }

    public int generalPurposeBitsShift() {
        return supplier.systemInfoBitSize() + supplier.categoryBitSize() + supplier.instanceDetailBitSize();
    }

    public int systemInfoBitsShift() {
        return supplier.categoryBitSize() + supplier.instanceDetailBitSize();
    }

    public int categoryBitsShift() {
        return supplier.instanceDetailBitSize();
    }

    public long generalPurposeBits() {
        return generalPurposeBits;
    }

    public long systemInfoBits() {
        return systemInfoBits;
    }

    public long categoryBits() {
        return categoryBits;
    }

    public long instanceBits() {
        return instanceBits;
    }

    public enum LongGeneralPurposeFeatures {
        ALL,
        READ,
        UPDATE,
        SUBITEM_READ,
        SUBITEM_UPDATE
    }
}