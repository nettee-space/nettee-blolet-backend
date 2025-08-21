package nettee.blolet.jwt.filter;

import lombok.RequiredArgsConstructor;
import nettee.blolet.jwt.filter.properties.AuthorizationFilterProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.pattern.PathPatternParser;

@Configuration
@RequiredArgsConstructor
public class JwtFilterParserConfig {

    @Bean
    public MethodPathPatternParser methodPathPatternParser(
            AuthorizationFilterProperties properties,
            PathPatternParser patternParser
    ) {
        return new MethodPathPatternParser(properties, patternParser);
    }
}
