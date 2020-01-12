package com.microservice.phonekart.apigateway.model;

import java.io.Serializable;

public class JwtResponse implements Serializable  {

	private static final long serialVersionUID = -8091879091924046844L;
	private  String jwttoken;
	
	private String message;
	
	private String error;
	

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
	
	public void setToken(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
