package nettee.jwt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * JWT 토큰 내의 사용자 정보를 주입받기 위한 어노테이션
 * 컨트롤러 메서드의 파라미터에 사용하여 현재 인증된 사용자 정보를 자동으로 주입받을 수 있다.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthUser {
}

