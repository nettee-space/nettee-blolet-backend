package nettee.jwt.filter;

import nettee.jwt.filter.AuthorizationFilterProperties.PathFilterProperties;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static nettee.jwt.filter.AuthorizationFilterProperties.ALL_METHOD_SIGN;

public class MethodPathPatternParser {
    private final PathPatternParser patternParser;
    private final Map<String, Set<PathPattern>> excludePathsMap;

    public MethodPathPatternParser(AuthorizationFilterProperties properties, PathPatternParser patternParser) {
        this.patternParser = patternParser;
        this.excludePathsMap = convertToPatterns(properties.getAllExcludePathsMap());
    }

    /**
     *
     * @return 등록한 모든 경로의 HTTP 메서드 집합을 반환합니다.
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
     *           <td>HTTP 메서드</td>
     *       </tr>
     *       <tr>
     *           <th>Value</th>
     *           <td>그 메서드가 인가를 우회하도록 설정한 모든 경로 패턴</td>
     *       </tr>
     *   </table>
     */
    public Map<String, Set<PathPattern>> getAllExcludePathsMap() {
        return excludePathsMap;
    }

    /**
     *
     * @param method
     * @return
     */
    public Set<PathPattern> getExcludePathsByMethod(String method) {
        Set<PathPattern> set = excludePathsMap.get(method.toLowerCase());

        return set != null ?
                set :
                excludePathsMap.get(ALL_METHOD_SIGN);
    }

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
