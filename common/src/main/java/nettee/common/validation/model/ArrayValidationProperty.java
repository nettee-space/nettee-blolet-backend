package nettee.common.validation.model;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import nettee.common.validation.model.interfaces.LengthValidationProperty;

import java.util.HashMap;
import java.util.Map;

import static nettee.common.validation.model.interfaces.BaseValidationProperty.ReservedFieldNames.MAX_LENGTH;
import static nettee.common.validation.model.interfaces.BaseValidationProperty.ReservedFieldNames.MIN_LENGTH;
import static nettee.common.validation.model.interfaces.BaseValidationProperty.ReservedFieldNames.REQUIRED;

@Builder
@Slf4j
public record ArrayValidationProperty(
        String type,
        Boolean required,
        Integer minLength,
        Integer maxLength,
        Map<String, String> messages
) implements LengthValidationProperty {
    public ArrayValidationProperty {
        assert type == null || type.isBlank() || "array".equals(type)
                : "type must be 'array'. If new spec types are added, update this assertion accordingly.\n" +
                "type 속성은 반드시 'array'이어야 합니다. 만약 새로운 값을 추가한다면 이 assert 구문을 함께 고쳐야 합니다.";

        if (type == null || type.isBlank()) {
            log.warn("ArrayValidationProperty had a blank 'type' property. Setting 'type' to 'array'.");
            type = "array";
        }

        if (required == null) {
            required = false;
        }

        // 둘 다 존재한다면 min < max (where, 둘 다 음수가 아님.)
        assert minLength == null || maxLength == null || (minLength >= 0 && maxLength >= 0 && minLength <= maxLength)
                : "Invalid length bounds: minLength must be >= 0, maxLength must be >= 0, and minLength <= maxLength.";

        if (messages == null && (required || minLength != null || maxLength != null)) {
            messages = new HashMap<>();
        }

        if (required && !messages.containsKey(REQUIRED)) {
            messages.put(REQUIRED, "필수 입력 항목입니다.");
        }

        if (minLength != null && !messages.containsKey(MIN_LENGTH)) {
            messages.put(MIN_LENGTH, minLength + "개 원소 이상을 입력해야 합니다.");
        }

        if (maxLength != null && !messages.containsKey(MAX_LENGTH)) {
            messages.put(MAX_LENGTH, "최대 " + maxLength + "개 원소까지 입력할 수 있습니다.");
        }

        assert messages != null;
        messages = Map.copyOf(messages);
    }
}
