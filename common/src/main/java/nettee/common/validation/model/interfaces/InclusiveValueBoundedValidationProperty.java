package nettee.common.validation.model.interfaces;

import java.util.List;

public interface InclusiveValueBoundedValidationProperty extends ValueBoundedValidationProperty {
    List<Boolean> inclusive();
}
