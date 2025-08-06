package nettee.auth.redis.adapter;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import nettee.auth.port.AuthRedisPort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthRedisAdapter implements AuthRedisPort {

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void save(String key, String value, Duration ttl) {
        stringRedisTemplate.opsForValue().set(key, value, ttl);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }
}
