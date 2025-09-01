package nettee.blolet.jwt.filter.resolver;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import nettee.blolet.jwt.filter.annotation.AuthUser;
import nettee.blolet.jwt.filter.annotation.AuthorizedUser;
import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

/**
 * @AuthUser 어노테이션이 붙은 파라미터를 처리하여 AuthorizedUser 객체를 주입하는 ArgumentResolver
 */
@Component
@RequiredArgsConstructor
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 얼리리턴
        if (!parameter.hasParameterAnnotation(AuthUser.class)) {
            return false;
        }
        // AuthorizedUser 직접 타입
        if (parameter.getParameterType().equals(AuthorizedUser.class)) {
            return true;
        }

        // Optional<AuthorizedUser> 제네릭 타입
        if (parameter.getParameterType().equals(Optional.class)) {
            Class<?> genericType = ResolvableType.forMethodParameter(parameter).getGeneric(0).resolve();
            return AuthorizedUser.class.equals(genericType);
        }

        return false;
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        AuthorizedUser user = (AuthorizedUser) request.getAttribute(AuthorizedUser.class.getTypeName());

        if (parameter.getParameterType().equals(Optional.class)) {
            return Optional.ofNullable(user);
        }

        return user;
    }
}
