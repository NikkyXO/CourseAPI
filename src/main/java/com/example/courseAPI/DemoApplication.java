package com.example.courseAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.courseAPI.security.authentication.AuthenticationService;
import com.example.courseAPI.security.authentication.RegisterRequest;
import com.example.courseAPI.security.role.Role;


@SpringBootApplication
public class DemoApplication {
	
	   public static void main(String[] args) {
	      SpringApplication.run(DemoApplication.class, args);
	   }
	   
//	   @Bean
//	   public CommandLineRunner commandLineRunner(
//				AuthenticationService service
//		) {
//			return args -> {
//				var admin = RegisterRequest.builder()
//						.firstname("Admin")
//						.lastname("Admin")
//						.email("admin@mail.com")
//						.password("password")
//						.role(Role.ADMIN)
//						.build();
//				System.out.println("Admin token: " + service.register(admin).getAccessToken());
//
//				var manager = RegisterRequest.builder()
//						.firstname("Admin")
//						.lastname("Admin")
//						.email("manager@mail.com")
//						.password("password")
//						.role(Role.MANAGER)
//						.build();
//				System.out.println("Manager token: " + service.register(manager).getAccessToken());
//
//			};
//		}

}


