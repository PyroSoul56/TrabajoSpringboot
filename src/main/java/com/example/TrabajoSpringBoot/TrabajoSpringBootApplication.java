package com.example.TrabajoSpringBoot;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrabajoSpringBootApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();
		dotenv.entries().forEach(
				entry -> System.setProperty(entry.getKey(), entry.getValue())
		);
		SpringApplication.run(TrabajoSpringBootApplication.class, args);
	}


}
