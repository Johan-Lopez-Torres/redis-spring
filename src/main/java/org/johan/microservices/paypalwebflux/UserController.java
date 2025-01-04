package org.johan.microservices.paypalwebflux;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        log.info("UserController created");
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Mono<User> getUserById(@PathVariable String id) {
        log.info("get user by id: {} founded", id);
        return userService.findById(id);

    }

    @GetMapping
    public Flux<User> getAllUsers() {
        log.info("get all users");
        return userService.findAll();
    }

    @PostMapping
    public Mono<User> createUser(@RequestBody User user) throws JsonProcessingException {
        log.info("create user: {}", user.getId());
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable String id) {
        log.info("delete user by id: {}", id);
        return userService.deleteById(id);
    }
}
