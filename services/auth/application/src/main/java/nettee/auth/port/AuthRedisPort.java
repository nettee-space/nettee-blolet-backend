package nettee.auth.port;

public interface AuthRedisPort {
    void saveRefreshToken(String refreshTokenKey, String refreshToken);
}
