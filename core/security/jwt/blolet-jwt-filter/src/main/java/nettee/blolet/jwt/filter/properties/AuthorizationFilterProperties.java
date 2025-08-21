package nettee.blolet.jwt.filter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@ConfigurationProperties(prefix = "app.auth.filter")
public class AuthorizationFilterProperties {

    public static final String ALL_METHOD_SIGN = "*";

    private final Map<String, Set<PathFilterProperties>> excludePathsMap;

    /**
     *
     * @param excludePaths JWT 토큰 검증을 우회하는
     */
    public AuthorizationFilterProperties(Set<PathFilterProperties> excludePaths) {
        this.excludePathsMap = groupByMethod(excludePaths);
    }

    /**
     * 등록된 모든 HTTP 메서드 집합을 반환합니다.
     *
     * @return 인가 우회 경로가 등록된 HTTP 메서드 이름 집합
     */
    public Set<String> getAllRegisteredMethods() {
        return excludePathsMap.keySet();
    }

    /**
     *
     * @return 등록한 모든 경로를 HTTP 메서드를 키로 한 맵 구조로 반환합니다.
     *   <table>
     *       <tr>
     *           <th>Key</th>
     *           <td>HTTP 메서드 (소문자 또는 "*" 와일드카드)</td>
     *       </tr>
     *       <tr>
     *           <th>Value</th>
     *           <td>해당 메서드에 대하여 인가를 우회하는 경로 집합</td>
     *       </tr>
     *   </table>
     */
    public Map<String, Set<PathFilterProperties>> getAllExcludePathsMap() {
        return excludePathsMap;
    }

    /**
     * 특정 HTTP 메서드에 대해 인가 우회가 설정된 경로 집합을 반환합니다.
     *
     * @param method 조회할 HTTP 메서드 (예: "get", "post", "put"...)
     * @return 해당 메서드의 인가 우회 경로 집합.
     *         해당 메서드가 없을 경우 "*" 와일드카드에 해당하는 경로 집합을 반환합니다.
     */
    public Set<PathFilterProperties> getExcludePathsByMethod(String method) {
        Set<PathFilterProperties> set = excludePathsMap.get(method.toLowerCase());

        return set != null ?
                set :
                excludePathsMap.get(ALL_METHOD_SIGN);
    }

    /**
     * 주어진 {@code excludePaths} 를 HTTP 메서드별로 그룹화하여 불변 맵으로 반환합니다.
     * <ul>
     *     <li>"*" (ALL_METHOD_SIGN)이 지정된 경우: 모든 메서드에 공통 적용</li>
     *     <li>그 외: 각 메서드별 집합에 경로 추가</li>
     *     <li>마지막에 와일드카드("*")에 등록된 경로들을 모든 메서드 집합에 병합</li>
     * </ul>
     *
     * @param excludePaths 인가 우회 경로/메서드 정의 집합
     * @return 메서드별 인가 우회 경로 맵 (불변)
     */
    private Map<String, Set<PathFilterProperties>> groupByMethod(Set<PathFilterProperties> excludePaths) {
        var mutableMap = new ConcurrentHashMap<String, Set<PathFilterProperties>>();
        var excludePathsMap = new ConcurrentHashMap<String, Set<PathFilterProperties>>();

        // 메서드별 분류 (Mutable map & set)
        for (var pathItem : excludePaths) {
            var methods = pathItem.methods();

            // "*"을 포함 시 다른 메서드별 세트에는 담지 않고 "*" 세트에 담기
            if (methods.contains(ALL_METHOD_SIGN)) {
                var set = mutableMap.computeIfAbsent(ALL_METHOD_SIGN, (m) -> new HashSet<>());
                set.add(pathItem);
                continue;
            }

            // 메서드별 세트에 담기
            for (var method : pathItem.methods()) {
                var set = mutableMap.computeIfAbsent(method, (m) -> new HashSet<>());
                set.add(pathItem);
            }
        }

        // 와일드카드("*") 등록 경로를 모든 세트에 병합 후 불변 집합으로 변환
        var wildcardMethodPaths = mutableMap.get(ALL_METHOD_SIGN);
        for (var entry : mutableMap.entrySet()) {
            var key = entry.getKey();
            var set = entry.getValue();

            // "*" 동일 참조 or "*" 키는 그대로 복사
            if (set == wildcardMethodPaths || ALL_METHOD_SIGN.equals(key)) {
                excludePathsMap.put(key, Set.copyOf(set));
                continue;
            }

            assert wildcardMethodPaths != null;
            set.addAll(wildcardMethodPaths);
            excludePathsMap.put(key, Set.copyOf(set));
        }

        // 최종적으로 불변 맵으로 반환
        return Map.copyOf(excludePathsMap);
    }

    /**
     * 경로별 인가 우회 속성을 나타내는 레코드입니다.
     *
     * @param path        인가 검증을 우회할 경로 패턴 (예: "/auth/login", "/swagger-ui/**")
     * @param methods     해당 경로에 적용할 HTTP 메서드 집합.
     *                    HTTP 표준에서 커스텀 메서드를 허용하므로 enum 대신 문자열 집합으로 받습니다.
     *                    지정하지 않거나 비어 있으면 "*" (모든 메서드)로 대체됩니다.
     * @param description 경로에 대한 설명 (nullable → 기본값 "")
     */
    public record PathFilterProperties(
            String path,
            Set<String> methods,
            String description
    ) {
        public PathFilterProperties {
            if (methods == null || methods.isEmpty()) {
                methods = Set.of("*");
            }

            if (description == null) {
                description = "";
            }

            methods = methods.stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet());
        }
    }
}