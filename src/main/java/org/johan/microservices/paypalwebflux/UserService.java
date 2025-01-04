package org.johan.microservices.paypalwebflux;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.management.monitor.StringMonitor;
import java.security.PrivateKey;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RedisTemplate<String, Object> redisTemplate;


    public void saveUserAsHash(User user) {

        userRepository.save(user);

        String userKey = "USER:" + user.getId();
        redisTemplate.opsForHash().put(userKey, "user_id", user.getId());
        redisTemplate.opsForHash().put(userKey, "user_name", user.getName());
        redisTemplate.opsForHash().put(userKey, "user_email", user.getEmail());
        redisTemplate.opsForHash().put(userKey, "user_age", user.getAge());
        redisTemplate.opsForHash().put(userKey, "user_is_married", user.getIsMarried());
        redisTemplate.expire(userKey, 180, TimeUnit.SECONDS);
    }


    @Cacheable(cacheNames = "users", key = "#id")
    public Mono<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Cacheable(cacheNames = "users")
    public Flux<User> findAll() {
        return userRepository.findAll();
    }


    @CachePut(cacheNames = "users", key = "#user.id")
    public Mono<User> save(User user) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(user);
        return userRepository.save(user)
                .doOnSuccess(savedUser -> {
                    log.info("User saved: {}", json);

                });

    }

    @CacheEvict(cacheNames = "users", key = "#id")
    public Mono<Void> deleteById(String id) {
        return userRepository.deleteById(id);
    }
}