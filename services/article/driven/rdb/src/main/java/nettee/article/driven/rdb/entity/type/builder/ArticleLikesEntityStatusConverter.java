package nettee.article.driven.rdb.entity.type.builder;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ArticleLikesEntityStatusConverter implements AttributeConverter<ArticleLikesEntityStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ArticleLikesEntityStatus status) {
        return status != null ? status.getCode() : null;
    }

    @Override
    public ArticleLikesEntityStatus convertToEntityAttribute(Integer value) { return ArticleLikesEntityStatus.valueOf(value); }
}
