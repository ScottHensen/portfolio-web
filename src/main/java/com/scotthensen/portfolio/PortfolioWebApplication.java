package com.scotthensen.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration		//TODO: remove.  using for auto-ddl
@ComponentScan
public class PortfolioWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioWebApplication.class, args);
	}
}
