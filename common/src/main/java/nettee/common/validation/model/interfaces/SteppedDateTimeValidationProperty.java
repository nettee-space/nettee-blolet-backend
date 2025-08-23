package nettee.common.validation.model.interfaces;

public interface SteppedDateTimeValidationProperty extends DateTimeBaseValidationProperty {
    String step();
    String stepEpoch();
}
