package nettee.auth.port;

public interface AuthRedisPort {
    String generateRefreshToken(String loginId, String refreshTokenKey);
    String generateAccessToken(String loginId);
}
