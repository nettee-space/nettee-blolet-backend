package nettee.auth.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.cookie.refresh-token")
public class RefreshTokenConfig {
    private boolean httpOnly;
    private boolean secure;
    private String path;
    private int maxAge;
    private String sameSite;
}
