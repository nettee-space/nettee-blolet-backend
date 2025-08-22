package nettee.common;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

// TODO common에 두어도 괜찮을지 논의 후 옮기거나 유지하기
@Configuration
public class CommonJacksonConfig {

    /**
     * BigDecimal을 JSON 직렬화 시 문자열(String)로 변환하도록 설정합니다.
     * <p>
     * - 직렬화(응답): BigDecimal → "123.45"와 같이 문자열로 직렬화합니다.
     * - 역직렬화(요청): Jackson 기본 동작을 이용하므로,
     *   숫자(123.45)와 문자열("123.45") 모두 BigDecimal 필드로 매핑됩니다.
     * <p>
     */
    @Bean
    Jackson2ObjectMapperBuilderCustomizer bigDecimalAsString() {
        return builder ->
                builder.serializerByType(BigDecimal.class, ToStringSerializer.instance);
    }
}