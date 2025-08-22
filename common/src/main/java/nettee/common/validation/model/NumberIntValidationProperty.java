package nettee.common.validation.model;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import nettee.common.validation.model.interfaces.ValueBoundedValidationProperty;

import java.util.HashMap;
import java.util.Map;

import static nettee.common.validation.model.interfaces.BaseValidationProperty.ReservedFieldNames.MAX;
import static nettee.common.validation.model.interfaces.BaseValidationProperty.ReservedFieldNames.MIN;
import static nettee.common.validation.model.interfaces.BaseValidationProperty.ReservedFieldNames.REQUIRED;

@Builder
@Slf4j
public record NumberIntValidationProperty(
        String type,
        Boolean required,
        Long min,
        Long max,
        Map<String, String> messages
) implements ValueBoundedValidationProperty {
    public NumberIntValidationProperty {
        assert type == null || type.isBlank() || "number:int".equals(type)
                : "type must be 'number:int'. If new spec types are added, update this assertion accordingly.\n" +
                "type 속성은 반드시 'number:int'이어야 합니다. 만약 새로운 값을 추가한다면 이 assert 구문을 함께 고쳐야 합니다.";
        assert min == null || max == null || (min >= 0 && max >= 0 && min <= max)
                : "Invalid length bounds: min must be >= 0, max must be >= 0, and min <= max.";

        // type을 지정하지 않았다면 "number:int"로 초기화합니다.
        if (type == null || type.isBlank()) {
            type = "number:int";
        }

        // 스펙상 `required`는 생략 시 `false`로 취급합니다. (생략해도 되지만, 명시적으로 전달하기 위해 초기화함.)
        if (required == null) {
            required = false;
        }

        // messages는 안내가 필요한 항목이 있다면 반드시 포함되어야 합니다.
        if (messages == null && (required || min != null || max != null)) {
            messages = new HashMap<>();
        }

        if (required && !messages.containsKey(REQUIRED)) {
            messages.put(REQUIRED, "필수 입력 항목입니다.");
        }

        if (min != null && !messages.containsKey(MIN)) {
            messages.put(MIN, min + " 이상을 입력해야 합니다.");
        }

        if (max != null && !messages.containsKey(MAX)) {
            messages.put(MAX, "최대 " + max + "까지 입력할 수 있습니다.");
        }

        assert messages != null;
        messages = Map.copyOf(messages);
    }
}
