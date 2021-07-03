package com.fresco.healthcare.model;


public class Response {

	private String message;
	private String id;
	private String token;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Response(String message) {
		super();
		this.message = message;
	}

	public Response(String message, String token, String id) {
		this.message = message;
		this.id = id;
		this.token = token; 
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
