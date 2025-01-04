package org.johan.microservices.paypalwebflux;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class UserServiceTest {

//    @Test
//    public void testSave() throws JsonProcessingException {
//        // Arrange
//        UserRepository mockRepository = Mockito.mock(UserRepository.class);
//        UserService userService = new UserService(mockRepository);
//        User user = new User("1", "Test", "test@example.com", 25, true);
//        Mockito.when(mockRepository.save(user)).thenReturn(Mono.just(user));
//
//        // Act
//        Mono<User> result = userService.save(user);
//
//        // Assert
//        StepVerifier.create(result)
//                .expectNext(user)
//                .verifyComplete();
//    }
}