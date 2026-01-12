package com.spring;

import org.springframework.stereotype.Component;


// here we are going to create bean by class with Annotation (@Component).
@Component
public class Demo2Service{
	
	public void printMessage() {
		System.out.println("Demo2Service method is execute ");
	}
}