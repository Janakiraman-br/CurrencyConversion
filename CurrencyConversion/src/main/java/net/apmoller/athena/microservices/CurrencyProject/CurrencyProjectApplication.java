package net.apmoller.athena.microservices.CurrencyProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class  CurrencyProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyProjectApplication.class, args);
		System.out.println("Hello from currency");
	}

}
