package nettee.jwt.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nettee.jwt.JwtParser;
import nettee.jwt.annotation.AuthorizedUser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * JWT 인가 필터
 * HTTP 요청에서 JWT 토큰을 추출하고 검증하여 인가를 처리합니다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtParser jwtParser;
    private final JwtFilterConfig filterConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws IOException, ServletException {
        
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        
        // 1. Authorization 헤더에서 JWT 토큰 추출
        String jwtToken = extractJwtToken(request);

        // 2. JWT 토큰이 없으면 401 Unauthorized 응답
        if (jwtToken == null) {
            log.warn("JWT 토큰이 존재하지 않습니다. [{}]: {}", method, requestURI);
            sendUnauthorizedResponse(response, "JWT 토큰이 존재하지 않습니다.", "Authorization 헤더에 JWT 토큰이 존재하지 않습니다.");
            return;
        }

        try {
            // 3. JWT 토큰 검증 및 파싱
            var claims = jwtParser.parseClaims(jwtToken);


            // 4. JWT 토큰에서 사용자 정보 추출
            // 런타임에 제네릭 타입 정보가 지워지면서, JWT Claim의 roles와 profileIds List 타입을 알 수가 없어 나오는 경고
            // JWT 생성 시, List<String> 타입을 보장하기 때문에 @SuppressWarnings("unchecked") 사용
            @SuppressWarnings("unchecked")
            var authUser = AuthorizedUser.builder()
                    .userId(claims.getSubject())
                    .roles(Optional.ofNullable((List<String>) claims.get("roles", List.class))
                            .orElse(Collections.emptyList()))
                    .profileIds(Optional.ofNullable((List<String>) claims.get("profileIds", List.class))
                            .orElse(Collections.emptyList()))
                    .build();
            request.setAttribute("authUser", authUser);

            filterChain.doFilter(request, response);
            
        } catch (ExpiredJwtException e) {
            log.warn("JWT 토큰이 만료되었습니다.");
            sendUnauthorizedResponse(response, "JWT 토큰이 만료되었습니다.", e.getMessage());

        } catch (JwtException e) {
            log.error("JWT 토큰이 유효하지 않습니다.");
            sendUnauthorizedResponse(response, "JWT 토큰이 유효하지 않습니다.", e.getMessage());
        }
    }

    /**
     * JWT 토큰이 필요하지 않은 경로는 바로 통과
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String requestURI = request.getRequestURI();

        return filterConfig.getExcludePaths().stream()
                .anyMatch(requestURI::startsWith);
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
