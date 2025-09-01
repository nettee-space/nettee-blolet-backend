package nettee.series.driving.web

import nettee.blolet.jwt.filter.resolver.AuthUserArgumentResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@TestConfiguration
class ResolverTestConfig : WebMvcConfigurer {
    @Autowired
    private lateinit var authUserArgumentResolver: AuthUserArgumentResolver

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(authUserArgumentResolver)
    }
}