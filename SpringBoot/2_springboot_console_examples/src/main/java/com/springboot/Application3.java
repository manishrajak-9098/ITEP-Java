package com.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application3 {

	public static void main(String[] args) {
		SpringApplication.run(Application3.class, args);
		System.out.println("This is my first spring boot example");
		
	}
	
	@Bean
	public CommandLineRunner printMessage() {
		return args->{
			System.out.println("this is eaxample of ccommand line runner");;
		};
	}
	
	
}
