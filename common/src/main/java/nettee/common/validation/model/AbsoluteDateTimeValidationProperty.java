package nettee.common.validation.model;

import nettee.common.validation.model.interfaces.SinceUntilDateTimeValidationProperty;
import nettee.common.validation.model.interfaces.SteppedDateTimeValidationProperty;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static nettee.common.validation.model.interfaces.BaseValidationProperty.ReservedFieldNames.REQUIRED;
import static nettee.common.validation.model.interfaces.BaseValidationProperty.ReservedFieldNames.SINCE;
import static nettee.common.validation.model.interfaces.BaseValidationProperty.ReservedFieldNames.UNTIL;

public record AbsoluteDateTimeValidationProperty(
        String type,
        Boolean required,
        Instant since,
        Instant until,
        List<Boolean> inclusive,
        String step,
        String stepEpoch,
        Map<String, String> messages
) implements SinceUntilDateTimeValidationProperty, SteppedDateTimeValidationProperty {
    public AbsoluteDateTimeValidationProperty {
        assert type == null || type.isBlank() || "datetime:absolute".equals(type)
                : "type must be 'datetime:absolute'. If new spec types are added, update this assertion accordingly.\n"
                + "type 속성은 반드시 'datetime:absolute'여야 합니다. 만약 새로운 값을 추가한다면 이 assert 구문을 함께 고쳐야 합니다.";
        assert since == null || until == null || !since.isAfter(until) : "since must be <= until when both are provided.";
        assert inclusive == null || inclusive.size() == 2 : "Invalid inclusive size: inclusive.size() == 2";
        assert stepEpoch == null || stepEpoch.isBlank() || (step != null && !step.isBlank())
                : "stepEpoch requires step. If stepEpoch is provided, step must also be provided.";

        if (type == null || type.isBlank()) {
            type = "datetime:absolute";
        }
        if (required == null) {
            required = false;
        }

        if (step != null && step.isBlank()) {
            step = null;
        }
        if (stepEpoch != null && stepEpoch.isBlank()) {
            stepEpoch = null;
        }

        // messages: 안내가 필요한 항목이 있으면 맵 생성
        if (messages == null && (required || since != null || until != null)) {
            messages = new HashMap<>();
        }

        // 기본 안내문 자동 채움(키가 존재할 때만 덮어씀)
        if (required && !messages.containsKey(REQUIRED)) {
            messages.put(REQUIRED, "필수 입력 항목입니다.");
        }
        if (since != null && !messages.containsKey(SINCE)) {
            messages.put(SINCE, since + " 이후만 선택할 수 있습니다.");
        }
        if (until != null && !messages.containsKey(UNTIL)) {
            messages.put(UNTIL, "최대 " + until + "까지 선택할 수 있습니다.");
        }

        // 불변화
        assert messages != null;
        messages = java.util.Map.copyOf(messages);
    }
}
