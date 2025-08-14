package nettee.jwt.filter;

import java.util.Set;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties(prefix = "blolet.jwt.filter.exclude-paths")
public class JwtFilterConfig {
    /**
     * JWT 토큰 검증이 필요하지 않은 경로들
     */
    private Set<String> excludePaths = Set.of();

    public void setExcludePaths(Set<String> excludePaths) {
        this.excludePaths = (excludePaths == null) ? Set.of() : Set.copyOf(excludePaths);
    }
}