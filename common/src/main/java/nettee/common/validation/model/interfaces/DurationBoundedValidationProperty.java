package nettee.common.validation.model.interfaces;

import java.util.List;

public interface DurationBoundedValidationProperty extends DateTimeBaseValidationProperty {
    DurationValidationSpec minDuration();
    DurationValidationSpec maxDuration();
    List<Boolean> inclusive();
}
