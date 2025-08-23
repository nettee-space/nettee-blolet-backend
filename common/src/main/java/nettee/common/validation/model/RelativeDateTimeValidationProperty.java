package nettee.common.validation.model;

import nettee.common.validation.model.interfaces.DurationBoundedValidationProperty;
import nettee.common.validation.model.interfaces.DurationValidationSpec;
import nettee.common.validation.model.interfaces.SteppedDateTimeValidationProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static nettee.common.validation.model.interfaces.BaseValidationProperty.ReservedFieldNames.MAX_DURATION;
import static nettee.common.validation.model.interfaces.BaseValidationProperty.ReservedFieldNames.MIN_DURATION;
import static nettee.common.validation.model.interfaces.BaseValidationProperty.ReservedFieldNames.REQUIRED;

public record RelativeDateTimeValidationProperty(
        String type,
        Boolean required,
        DurationValidationSpec minDuration,
        DurationValidationSpec maxDuration,
        List<Boolean> inclusive,
        String step,
        String stepEpoch,
        Map<String, String> messages
) implements DurationBoundedValidationProperty, SteppedDateTimeValidationProperty {
    public RelativeDateTimeValidationProperty {
        assert type == null || type.isBlank() || "datetime:relative".equals(type)
                : "type must be 'datetime:relative'. If new spec types are added, update this assertion accordingly.\n"
                + "type 속성은 반드시 'datetime:relative'여야 합니다. 만약 새로운 값을 추가한다면 이 assert 구문을 함께 고쳐야 합니다.";
        assert minDuration == null || maxDuration == null || minDuration.ref().equals(maxDuration.ref())
                : "minDuration.ref and maxDuration.ref must match when both are provided. 지금은 동일한 ref만 지원합니다.";
        assert inclusive == null || inclusive.size() == 2 : "Invalid inclusive size: inclusive.size() == 2";

        // step, stepEpoch 값이 비어 있으면 null로 정리
        if (step != null && step.isBlank()) {
            step = null;
        }
        if (stepEpoch != null && stepEpoch.isBlank()) {
            stepEpoch = null;
        }

        // stepEpoch가 존재한다면 step도 반드시 존재해야 함
        assert stepEpoch == null || step != null : "If stepEpoch is provided, step must also be provided.";

        // 기본값 설정
        if (type == null || type.isBlank()) {
            type = "datetime:relative";
        }
        if (required == null) {
            required = false;
        }
        // 스펙상 `inclusive`는 생략 시 `[true, true]`로 취급됩니다. (생략해도 되지만, 명시적으로 전달하기 위해 초기화함.)
        if (inclusive == null && (minDuration != null || maxDuration != null)) {
            inclusive = List.of(true, true);
        }

        // messages: 안내가 필요한 항목이 있으면 맵 생성
        if (messages == null && (required || minDuration != null || maxDuration != null)) {
            messages = new HashMap<>();
        }

        // 기본 안내문 자동 채움(키가 존재할 때만 덮어씀: 다른 파일들과 동일한 패턴)
        if (required && !messages.containsKey(REQUIRED)) {
            messages.put(REQUIRED, "필수 입력 항목입니다.");
        }
        if (minDuration != null && !messages.containsKey(MIN_DURATION)) {
            messages.put(MIN_DURATION, minDuration.offset() + " 이후만 선택할 수 있습니다.");
        }
        if (maxDuration != null && !messages.containsKey(MAX_DURATION)) {
            messages.put(MAX_DURATION, "최대 " + maxDuration.offset() + " 이전까지만 선택할 수 있습니다.");
        }

        // 불변화
        assert messages != null;
        messages = Map.copyOf(messages);
    }
}
