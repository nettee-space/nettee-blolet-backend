package nettee.auth.port;

import java.time.Duration;

public interface AuthRedisPort {
    void save(String key, String value, Duration ttl);
    String get(String key);

    void delete(String key);
}
