package com.ownproject.ServiceOrganizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.ownproject.ServiceOrganizer.Services.UserService;

@SpringBootApplication
public class ServiceOrganizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(new Class[] { ServiceOrganizerApplication.class }, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	

}
