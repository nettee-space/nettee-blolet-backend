package nettee.blolet.article.rdb.entity.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class DraftBlockEntityStatusConverter implements AttributeConverter<DraftBlockEntityStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DraftBlockEntityStatus status) {
        return status != null ? status.getCode() : null;
    }

    @Override
    public DraftBlockEntityStatus convertToEntityAttribute(Integer value) { return DraftBlockEntityStatus.valueOf(value); }
}
