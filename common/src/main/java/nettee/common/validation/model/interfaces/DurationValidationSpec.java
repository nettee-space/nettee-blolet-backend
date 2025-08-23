package nettee.common.validation.model.interfaces;

import java.util.Arrays;

import static nettee.common.validation.model.interfaces.DurationValidationSpec.ResourceFieldRef.RESOURCE_FIELD_REF_PREFIX;

public record DurationValidationSpec(
        String ref,
        String offset
) {
    public DurationValidationSpec {
        assert ref != null : "Ref must not be null" ;
        assert offset != null : "Offset cannot be null";
        assert isSupportedRef(ref) :
                ref + " is not supported. " + "Use " +
                        Arrays.stream(ReservedRefTypes.values())
                                .map(Enum::name)
                                .<String>reduce((acc, cur) -> acc + ", " + cur) +
                        ", or " + RESOURCE_FIELD_REF_PREFIX + "<fieldName> instead." ;
        assert offset.matches("^P(?:(\\d+)Y)?(?:(\\d+)M)?(?:(\\d+)W)?(?:(\\d+)D)" +
                "?(?:T(?:(\\d+)H)?(?:(\\d+)M)?(?:(\\d+(?:\\.\\d+)?)S)?)?$")
                : "step must be a valid ISO-8601 duration (e.g. PT5M, P1D, P2W, P3M)";

    }

    public interface DateTimeValidationRef {
        String name();
    }

    private static boolean isSupportedRef(String ref) {
        for (var refEnumConstant : ReservedRefTypes.values()) {
            if (refEnumConstant.name().equals(ref)) {
                return true;
            }
        }

        return ref.startsWith(RESOURCE_FIELD_REF_PREFIX);
    }

    public static class ResourceFieldRef implements DateTimeValidationRef {

        public static final String RESOURCE_FIELD_REF_PREFIX = "RESOURCE_FIELD:";
        private final String fieldName;

        private ResourceFieldRef(String fieldName) {
            this.fieldName = fieldName;
        }

        public static ResourceFieldRef withFieldName(String fieldName) {
            return new ResourceFieldRef(fieldName);
        }

        public String name() {
            return RESOURCE_FIELD_REF_PREFIX + fieldName;
        }
    }

    public enum ReservedRefTypes implements DateTimeValidationRef {
        NOW,
        TODAY,
        RESOURCE_RELATIVE,
        RESOURCE_TZ_NOW,
        RESOURCE_TZ_TODAY,
    }
}
