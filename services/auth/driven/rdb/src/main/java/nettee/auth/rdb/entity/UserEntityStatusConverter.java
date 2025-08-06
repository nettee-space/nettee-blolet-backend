package nettee.auth.rdb.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserEntityStatusConverter implements AttributeConverter<UserEntityStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserEntityStatus entityStatus) {
        return entityStatus == null ? null : entityStatus.getCode();
    }

    @Override
    public UserEntityStatus convertToEntityAttribute(Integer dbCode) {
        return dbCode == null ? null : UserEntityStatus.fromCode(dbCode);
    }
}