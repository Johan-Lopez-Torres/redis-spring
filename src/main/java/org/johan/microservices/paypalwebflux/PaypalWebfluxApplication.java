package org.johan.microservices.paypalwebflux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PaypalWebfluxApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(PaypalWebfluxApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PaypalWebfluxApplication.class, args);
	}

//	@Autowired
//	private UserService userService;
//
//
//	@Override
//	public void run(String... args) throws Exception {
//		User user1 = new User();
//		user1.setName("Pedroaaaaaa");
//		user1.setEmail("Pedro@gmail.comaaa");
//		userService.save(user1).subscribe();
//
//	}

	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setId("0000");
		user1.setName("00067858");
		user1.setEmail("PedrjohanjohanjohanjohanoHash000");
		user1.setIsMarried(true);
		user1.setAge(25);
		userService.saveUserAsHash(user1);

		log.info("User hashed saved: {}", user1);


		User user2 = new User();
		user2.setId("0001");
		user2.setName("00067858");
		user2.setEmail("PedrjohanjohanjohanjohanoHash001");
		user2.setIsMarried(true);
		user2.setAge(25);
		userService.save(user2).subscribe(savedUser -> log.info("User 2 saved: {}", savedUser));
	}
}
