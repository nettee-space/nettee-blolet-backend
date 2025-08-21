package nettee.jwt.filter;

import nettee.jwt.filter.AuthorizationFilterProperties.PathFilterProperties;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static nettee.jwt.filter.AuthorizationFilterProperties.ALL_METHOD_SIGN;

/**
 * 인가 필터에서 HTTP 메서드별 경로 패턴을 파싱하고 관리하는 클래스입니다.
 * <p>
 *  {@link AuthorizationFilterProperties}로부터 전달받은 설정 경로를
 *  {@link PathPatternParser}를 통해 파싱하여 메서드별 {@link PathPattern} 맵으로 변환합니다.
 * </p>
 */
public class MethodPathPatternParser {
    private final PathPatternParser patternParser;
    private final Map<String, Set<PathPattern>> excludePathsMap;
    /**
     * 주어진 설정 정보를 기반으로 JWT 인가 우회 경로를 파싱하는 생성자입니다.
     *
     * @param properties    인가 우회 경로 설정 정보
     * @param patternParser 스프링 {@link PathPatternParser} 인스턴스
     */
    public MethodPathPatternParser(AuthorizationFilterProperties properties, PathPatternParser patternParser) {
        this.patternParser = patternParser;
        this.excludePathsMap = convertToPatterns(properties.getAllExcludePathsMap());
    }

    /**
     * 등록한 모든 HTTP 메서드 집합을 반환합니다.
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
    public Map<String, Set<PathPattern>> getAllExcludePathsMap() {
        return excludePathsMap;
    }

    /**
     * 주어진 HTTP 메서드에 해당하는 인가 우회 경로 패턴 집합을 반환합니다.
     * <ul>
     *     <li>특정 메서드가 등록되어 있으면 해당 경로 패턴들을 반환합니다.</li>
     *     <li>없을 경우 와일드카드(*) 메서드에 등록된 경로 패턴을 반환합니다.</li>
     * </ul>
     *
     * @param method 조회할 HTTP 메서드 이름 (예: "get", "post")
     * @return 해당 메서드 또는 와일드카드에 해당하는 경로 패턴 집합
     */
    public Set<PathPattern> getExcludePathsByMethod(String method) {
        Set<PathPattern> set = excludePathsMap.get(method.toLowerCase());

        return set != null ?
                set :
                excludePathsMap.get(ALL_METHOD_SIGN);
    }

    /**
     * 설정된 {@link PathFilterProperties} 맵을 {@link PathPattern} 맵으로 변환합니다.
     *
     * @param pathSet HTTP 메서드를 키로 한 {@link PathFilterProperties} 집합 맵
     * @return HTTP 메서드를 키로 한 {@link PathPattern} 집합 맵
     */
    private Map<String, Set<PathPattern>> convertToPatterns(Map<String, Set<PathFilterProperties>> pathSet) {
        var modifiableMap = new HashMap<String, Set<PathPattern>>();

        for (var entry : pathSet.entrySet()) {
            var method = entry.getKey();
            var pathProps = entry.getValue();

            modifiableMap.put(method, Set.copyOf(
                    pathProps.stream()
                            .map(PathFilterProperties::path)
                            .map(patternParser::parse)
                            .collect(Collectors.toSet())
            ));
        }

        return Map.copyOf(modifiableMap);
    }
}
