# JWT 인가 필터 (JWT Authorization Filter)

이 모듈은 Spring Boot 애플리케이션에서 **JWT(Json Web Token)** 기반 인가 처리를 수행하는 필터를 제공합니다.  
특정 경로나 HTTP 메서드 조합은 설정을 통해 **인가 검증을 우회(bypass)** 할 수 있습니다.

---

## 주요 기능

- **JWT 인가 필터 적용**
  - 요청 헤더에서 JWT를 추출하고 유효성을 검증합니다.
  - 인증 실패 시 적절한 예외 및 에러 응답을 반환합니다.
- **인가 필터 우회 경로 설정**
  - `application.yml`에서 특정 HTTP 메서드 + 경로를 설정하여 JWT 검증을 우회할 수 있습니다.
  - 예: 로그인, 회원가입, 헬스체크, Swagger Docs 등.
- **메서드/경로별 매핑 관리**
  - HTTP 메서드와 `PathPattern`을 기반으로 매칭합니다.
  - 와일드카드(`*`) 메서드 지원.

---

## 구성 요소

| 파일                                   | 설명                                                    |
|--------------------------------------|-------------------------------------------------------|
| `AuthorizationFilterProperties.java` | 인가 필터 설정을 관리하는 프로퍼티 클래스                               |
| `JwtAuthorizationFilter.java`        | JWT 토큰을 검증하는 실제 필터 구현체                                |
| `JwtFilterParserConfig.java`         | 설정된 exclude-paths를 `MethodPathPatternParser`로 변환 및 주입 |
| `PathFilterErrorCode.java`           | 인가 필터 관련 에러 코드 정의                                     |
| `PathFilterException.java`           | 인가 필터 관련 커스텀 예외 클래스                                   |
| `MethodPathPatternParser.java`       | 경로/메서드 패턴을 파싱하고 매칭 관리                                 |

---

## 설정 예시 (`application.yml`)

```yaml
app:
  auth:
    filter:
      exclude-paths:
        # 서버 간 통신
        - path: "/actuator/health"
          methods: "*"
          description: Spring Boot Actuator 헬스 체크
        - path: "/internal/**"
          methods: "*"
          description: 내부 API 경로
        
        # Swagger & Docs
        - path: "/swagger-ui/**"
          methods: "*"
        - path: "/v3/api-docs/**"
          methods: "*"
        
        # 인증/회원가입 API
        - path: "/auth/signup"
          methods: "*"
        - path: "/auth/login"
          methods: "*"
        - path: "/auth/email/verification/send"
          methods: "*"
        - path: "/auth/email/verification/check"
          methods: "*"
        - path: "/auth/token/refresh"
          methods: "*"
```

---

## 동작 흐름

1. `JwtAuthorizationFilter`가 모든 요청을 가로챕니다.
2. 요청 경로/메서드를 `MethodPathPatternParser`로 확인합니다.
  - exclude-paths에 매칭되면 JWT 검증을 우회합니다.
  - 매칭되지 않으면 JWT를 검증합니다.
3. JWT 검증에 실패하면 `PathFilterException`이 발생하고, `PathFilterErrorCode`에 따른 응답이 반환됩니다.

---

## 예외 처리

- **PathFilterException**
  - 잘못된 설정, 경로 누락, JWT 토큰 불일치 등 발생 시 던집니다.
- **PathFilterErrorCode**
  - `AUTH_FILTER_PATH_NOT_ENTERED`: 경로가 입력되지 않은 경우

---

## 활용 예시

- 아티클 서비스
  - **조회(READ)**: JWT 필요 없음 → exclude-paths 설정
  - **등록/수정/삭제(WRITE)**: JWT 필요
