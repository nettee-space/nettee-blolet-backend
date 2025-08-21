package nettee.main.config;

import lombok.RequiredArgsConstructor;
import nettee.blolet.jwt.filter.resolver.AuthUserArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthUserArgumentResolver authUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        // AuthUserArgumentResolver 추가하여 @AuthUser 어노테이션을 처리할 수 있도록 설정
        resolvers.add(authUserArgumentResolver);
    }
}
