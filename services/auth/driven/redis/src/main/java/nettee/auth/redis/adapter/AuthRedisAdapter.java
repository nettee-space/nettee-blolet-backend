package nettee.auth.redis.adapter;

import java.time.Duration;
import java.util.List;
import java.util.Set;
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

    @Override
    public void deleteAll(List<String> keys) {
        if (keys != null && !keys.isEmpty()) {
            stringRedisTemplate.delete(keys);
        }
    }

    @Override
    public void addToSet(String setKey, String value) {
        stringRedisTemplate.opsForSet().add(setKey, value);
    }

    @Override
    public void removeFromSet(String setKey, String value) {
        stringRedisTemplate.opsForSet().remove(setKey, value);
    }

    @Override
    public Set<String> getSetMembers(String userSetKey) {
        return stringRedisTemplate.opsForSet().members(userSetKey);
    }
}
