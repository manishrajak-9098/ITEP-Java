package com.spring.model;

public class User {
	int id;
	String username, email, address;
	
	public User() {
		
	}
	public User(int id, String username, String email, String address) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.address = address;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "User [uid=" + id + ", username=" + username + ", email=" + email + ", address=" + address + "]";
	}
	
	

}
