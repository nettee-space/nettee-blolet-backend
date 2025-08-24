package nettee.common.validation.model;

import lombok.Builder;
import nettee.common.validation.model.interfaces.BaseValidationProperty;

import java.time.Instant;
import java.util.Map;

@Builder
public record ValidationResponseModel(
        Map<String, BaseValidationProperty> validations,
        Instant serverTime,
        Instant lastUpdatedAt
) {
    public ValidationResponseModel {
        if (serverTime == null) {
            serverTime = Instant.now();
        }
    }
}
