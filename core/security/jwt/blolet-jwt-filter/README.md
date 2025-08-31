# JWT 인가 필터 (blolet-jwt-filter)

JWT 토큰을 사용한 인가 처리를 위한 필터 모듈입니다.  
초기 복잡성을 줄이기 위해 스프링 시큐리티 없이 구현합니다.

## 1. 주요 기능

- **인가 정책**: 인가가 필요하지 않은 경로에서의 요청은 통과 (Bypass)
- **JWT 토큰 검증**: HTTP 요청의 `Authorization` 헤더에서 `Bearer ` JWT를 추출하고 검증
- **`@AuthUser` 애노테이션**: 컨트롤러 메서드 파라미터에 로그인 사용자 정보를 자동으로 주입받음을 지시하는 커스텀 애노테이션

### 지원 파라미터 타입

이하 타입으로 선언한 파라미터에 `@AuthUser`를 붙일 수 있습니다.

- `AuthorizedUser`
- `Optional<AuthorizedUser>`

### 파라미터 타입별 인가 여부에 따른 기본값

- 인가가 필요한 요청
  - 주입 전 필터 단계에서 예외가 발생합니다.
- 인가를 거치지 않는 요청
  - `AuthorizedUser`: `null`이 주입될 수 있습니다.
  - `Optional<AuthorizedUser>`: `Optional.empty()`가 주입될 수 있습니다.

## 2. 의존성

- `jwt-parser` 모듈: JWT 토큰 파싱 및 검증
- `spring-boot-starter-web`: 스프링 웹 필터를 사용하기 위함

## 3. 사용 예시
### - @AuthUser 어노테이션 사용

컨트롤러 메서드 파라미터에서 `@AuthUser` 애노테이션 및 `AuthorizedUser` 객체로 인증된 사용자 정보를 자동으로 주입받을 수 있습니다.  

```java
@RestController
public class ExampleController {

    @GetMapping("/user-info")
    public ResponseEntity<String> getUserInfo(@AuthUser AuthorizedUser user) {
        // user 객체에 JWT 정보가 자동으로 주입됨
        String userId = user.getUserId();
        List<String> profileIds = user.getProfileIds();

        if (user.hasRole("ADMIN")) {
            return ResponseEntity.ok("Admin access granted");
        }

        if (user.hasProfileId("wch-os")) {
            return ResponseEntity.ok("Profile access granted");
        }

        return ResponseEntity.ok("User info: " + userId);
    }
}
```

## 4. 에러 처리

JWT 토큰 검증 실패 시 다음과 같은 응답을 반환합니다.

```json
{
  "error": "JWT 토큰이 만료되었습니다.",
  "message": "JWT has expired",
  "timestamp": "2025-01-01T00:00:00Z"
}
```

세 가지 케이스에 대하여 예외 처리를 합니다.

- 토큰 만료
- 토큰이 유효하지 않음 (잘못된 시그니처)
- 토큰이 존재하지 않음. (인가가 필요할 때)
