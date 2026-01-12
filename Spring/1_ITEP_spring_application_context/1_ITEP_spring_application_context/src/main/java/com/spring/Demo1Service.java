package com.spring;

public class Demo1Service{
	
	public String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void printMessage() {
		System.out.println("Demo1 Service Method Executes");
	}
}