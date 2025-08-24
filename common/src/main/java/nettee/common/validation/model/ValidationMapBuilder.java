package nettee.common.validation.model;

import nettee.common.validation.model.interfaces.BaseValidationProperty;

import java.util.HashMap;
import java.util.Map;

public final class ValidationMapBuilder {
    private final Map<String, BaseValidationProperty> map;

    private ValidationMapBuilder() {
        this.map = new HashMap<>();
    }

    public static ValidationMapBuilder builder() {
        return new ValidationMapBuilder();
    }

    public ValidationMapBuilder put(String fieldName, BaseValidationProperty validation) {
        map.put(fieldName, validation);
        return this;
    }

    public Map<String, BaseValidationProperty> build() {
        return Map.copyOf(map);
    }
}
