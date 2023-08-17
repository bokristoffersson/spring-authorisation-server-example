package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class AuthorizationserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationserverApplication.class, args);
	}

	@Bean
	InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		var user1 = User.withDefaultPasswordEncoder().roles("admin").username("axel").password("1Pass").build();
		var user2 = User.withDefaultPasswordEncoder().roles("user").username("urban").password("1Pass").build();
		return new InMemoryUserDetailsManager(user1, user2);
	}
}
