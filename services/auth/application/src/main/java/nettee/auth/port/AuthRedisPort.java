package nettee.auth.port;

public interface AuthRedisPort {
    void save(String key, String value);
    String get(String key);

    void delete(String key);
}
