package com.fresco.healthcare.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ApplicationUser {
	@Id
	 public String username;
     public String userEmail;
     public String password;
     public String user_mobile;
     public String location;
 
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_mobile() {
		return user_mobile;
	}
	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public ApplicationUser(String username, String userEmail, String password, String user_mobile, String location) {
		this.username = username;
		this.userEmail = userEmail;
		this.password = password;
		this.user_mobile = user_mobile;
		this.location = location;
	}
	public ApplicationUser() {
		super();
	}
	public ApplicationUser(String userName, String password) {
		super();
		this.username = userName;
		this.password = password;
	}
	
	//    public Date user_dob;
    
}
