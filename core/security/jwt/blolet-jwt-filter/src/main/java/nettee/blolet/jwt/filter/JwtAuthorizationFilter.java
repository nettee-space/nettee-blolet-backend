package nettee.blolet.jwt.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nettee.jwt.parser.JwtParser;
import org.springframework.http.server.PathContainer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

/**
 * JWT 인가 필터
 * HTTP 요청에서 JWT 토큰을 추출하고 검증하여 인가를 처리합니다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtParser jwtParser;
    private final MethodPathPatternParser methodPathPatternParser;

    /**
     * HTTP 요청을 필터링합니다.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        // 1. Authorization 헤더에서 JWT 토큰 추출
        String jwtToken = extractJwtToken(request);

        // 2. JWT 토큰이 없으면 401 Unauthorized 응답
        if (!StringUtils.hasText(jwtToken)) {
            log.warn("JWT 토큰이 존재하지 않습니다. [{}]: {}", method, requestURI);
            sendUnauthorizedResponse(response, "JWT 토큰이 존재하지 않습니다.", "Authorization 헤더에 JWT 토큰이 존재하지 않습니다.");
            return;
        }

        try {
            // 3. JWT 토큰 검증 및 파싱
            var claims = jwtParser.parseClaims(jwtToken);

            // 4. JWT 토큰에서 사용자 정보 추출
            setRequestAttribute(request, claims);

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            // 토큰이 만료된 경우, 리프레시 토큰 요청 경로는 통과
            if (requestURI.equals("/auth/token/refresh")) {
                var claims = e.getClaims();
                setRequestAttribute(request, claims);
                filterChain.doFilter(request, response);
            } else {
                log.warn("JWT 토큰이 만료되었습니다.");
                sendUnauthorizedResponse(response, "JWT 토큰이 만료되었습니다.", e.getMessage());
            }
        } catch (JwtException e) {
            log.error("JWT 토큰이 유효하지 않습니다.");
            sendUnauthorizedResponse(response, "JWT 토큰이 유효하지 않습니다.", e.getMessage());
        }
    }

    /**
     * JWT 토큰에서 사용자 정보를 추출하여 요청 속성에 설정합니다.
     */
    private void setRequestAttribute(HttpServletRequest request, Claims claims) {
        request.setAttribute("userId", claims.getSubject());
        request.setAttribute("roles", claims.get("roles", List.class));
        request.setAttribute("profileIds", claims.get("profileIds", List.class));
    }

    /**
     * JWT 토큰이 필요하지 않은 경로는 바로 통과
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        PathContainer pathContainer = PathContainer.parsePath(requestURI);
        String method = request.getMethod();

        // pattern 매칭을 통해 필터링 제외 경로인지 확인
        var patternSet = methodPathPatternParser.getExcludePathsByMethod(method);
        assert patternSet != null;
        return patternSet.stream()
                .anyMatch(pattern -> pattern.matches(pathContainer));
    }

    /**
     * HTTP 요청의 Authorization 헤더에서 JWT 토큰을 추출합니다.
     */
    private String extractJwtToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            return null;
        }

        return authorizationHeader.substring(7); // "Bearer " 제거
    }

    /**
     * 401 Unauthorized 응답을 전송합니다.
     */
    private void sendUnauthorizedResponse(HttpServletResponse response, String error, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String responseBody = String.format("""
            {
                "error": "%s",
                "message": "%s",
                "timestamp": "%s"
            }
            """, error, message, Instant.now());

        response.getWriter().write(responseBody);
    }
}
