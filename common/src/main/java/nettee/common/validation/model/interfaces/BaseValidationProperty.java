package nettee.common.validation.model.interfaces;

import java.util.Map;

public interface BaseValidationProperty {
    String type();
    Boolean required();
    Map<String, String> messages();

    final class ReservedFieldNames {
        // base
        public static final String TYPE = "type";
        public static final String REQUIRED = "required";
        public static final String MESSAGES = "messages";

        // length
        public static final String MIN_LENGTH = "minLength";
        public static final String MAX_LENGTH = "maxLength";

        // value bound
        public static final String MIN = "min";
        public static final String MAX = "max";
        public static final String INCLUSIVE = "inclusive";

        // duration
        public static final String MIN_DURATION = "minDuration";
        public static final String MAX_DURATION = "maxDuration";

        // step
        public static final String STEP = "step";
        public static final String STEP_EPOCH = "stepEpoch";
        
        private ReservedFieldNames() {}
    }

    final class ExpectedTypes {
        public static final String STRING = "string";
        public static final String NUMBER_INT = "number:int";
        public static final String NUMBER_DECIMAL = "number:decimal";
        public static final String ARRAY = "array";
        public static final String DATETIME_ABSOLUTE = "datetime:absolute";
        public static final String DATETIME_RELATIVE = "datetime:relative";
    }
}
