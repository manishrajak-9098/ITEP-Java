package com.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApplication{
	public static void main (String args[]) {
		
		ApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
	}
}