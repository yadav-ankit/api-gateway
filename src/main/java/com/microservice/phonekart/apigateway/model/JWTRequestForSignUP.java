package com.microservice.phonekart.apigateway.model;


public class JWTRequestForSignUP {

	private String username;
	private String password;
	private String name;
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//need default constructor for JSON Parsing
	public JWTRequestForSignUP()
	{
		
	}

	public JWTRequestForSignUP(String username, String password , String name , String email) {
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
		this.setName(name);
	}
	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
