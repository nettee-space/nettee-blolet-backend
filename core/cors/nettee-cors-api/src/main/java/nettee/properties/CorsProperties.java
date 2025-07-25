package nettee.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("app.cors")
public record CorsProperties (
        List<MappedCorsProperties> endpoints
) {
}