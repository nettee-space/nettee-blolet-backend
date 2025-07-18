package nettee.draft.draftblock.driven.rdb.entity.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import nettee.draft.draftblock.driven.rdb.entity.type.DraftBlockEntityStatus;

@Converter
public class DraftBlockEntityStatusConverter implements AttributeConverter<DraftBlockEntityStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DraftBlockEntityStatus status) {
        return status != null ? status.getCode() : null;
    }

    @Override
    public DraftBlockEntityStatus convertToEntityAttribute(Integer value) { return DraftBlockEntityStatus.valueOf(value); }
}
