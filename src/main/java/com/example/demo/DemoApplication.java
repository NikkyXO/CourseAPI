package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.jdbc.core.JdbcTemplate;


//to support WAR file deployment {extends}
//SpringBootServletInitializer
//and the overridden method
//@Override
//protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//   return application.sources(DemoApplication.class);
//}

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	
	   public static void main(String[] args) {
	      SpringApplication.run(DemoApplication.class, args);
	   }
	   
	   @Override
	   public void run(String... arg0) throws Exception {
	      System.out.println("Hello world from Command Line Runner");
	   }
}


