package com.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application5 {

	private final static Logger logger = LoggerFactory.getLogger(Application5.class);
	
	public static void main(String[] args) {
		System.out.println("Executes before");
		SpringApplication.run(Application5.class, args);
		
		logger.info("............this is the example of logger info");
		logger.error("...........this is the example of logger error");
		logger.debug("...........this is the example of debug logger");
	}
	
	
}
//slf4j = simple logging facade for java