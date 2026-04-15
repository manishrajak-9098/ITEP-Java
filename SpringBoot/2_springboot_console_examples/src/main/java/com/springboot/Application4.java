package com.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class Application4 {

	public static void main(String[] args) {
		SpringApplication.run(Application4.class, args);
		System.out.println("This is my first spring boot example");
		
	}
	
	@Bean
	@Order(1)
	public CommandLineRunner printMessage1() {
		return args->{
			System.out.println("this is eaxample of ccommand line runner_1");;
		};
	}
	
	@Bean
	@Order(2)
	public CommandLineRunner printMessage2() {
		return args ->{
			System.out.println("this is the example of command line runner 2");
		};
	}
	
	
}
