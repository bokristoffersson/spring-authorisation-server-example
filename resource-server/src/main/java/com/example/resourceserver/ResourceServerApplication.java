package com.example.resourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@EnableMethodSecurity
@SpringBootApplication
public class ResourceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceServerApplication.class, args);
	}

}

@Service
class GreetingsService {

	@PreAuthorize("hasAuthority('SCOPE_user:read')")
	public String greet() {
		var jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return "Hello " + jwt.getSubject();
	}
}

@Controller
@ResponseBody
class GreetingsController {

	private final GreetingsService service;

	GreetingsController(GreetingsService service) {
		this.service = service;
	}

	@GetMapping("/")
	String hello() {
		return this.service.greet();
	}

}