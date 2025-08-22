package nettee.common.validation.model.interfaces;

import java.time.Instant;
import java.util.List;

public interface SinceUntilDateTimeValidationProperty extends DateTimeBaseValidationProperty {
    Instant since();
    Instant until();
    List<Boolean> inclusive();
}
