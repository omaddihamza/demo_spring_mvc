package com.me.demospringmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DemoSpringMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringMvcApplication.class, args);
	}

}
