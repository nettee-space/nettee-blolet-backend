package nettee.common.validation.model.interfaces;

import java.util.Map;

public interface BaseValidationProperty {
    String type();
    Boolean required();
    Map<String, String> messages();
}
