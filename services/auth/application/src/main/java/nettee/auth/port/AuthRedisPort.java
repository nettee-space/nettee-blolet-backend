package nettee.auth.port;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public interface AuthRedisPort {
    // Key-Value operations
    void save(String key, String value, Duration ttl);
    String get(String key);
    void delete(String key);
    void deleteAll(List<String> keys);

    // Set operations
    void addToSet(String userSetKey, String hashedRefreshToken);
    void removeFromSet(String userSetKey, String hashedRefreshToken);
    Set<String> getSetMembers(String userSetKey);
}
