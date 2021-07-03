package com.fresco.healthcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fresco.healthcare.model.ApplicationUser;
import com.fresco.healthcare.repository.ApplicationUserRepository;


import java.util.List;


@Service
public class ApplicationUserService {

	@Autowired
	ApplicationUserRepository userRepository;
	
	public ApplicationUser register(ApplicationUser applicationUser) {
		ApplicationUser user = userRepository.findByUsername(applicationUser.getUsername());
		if(user != null) {
			return null;
		}
		else {
			userRepository.save(applicationUser);
			return applicationUser;
		}
	}

	public ApplicationUser validateLogin(String username, String password) {
		ApplicationUser user = userRepository.findByUsername(username);
		if(user != null) {
			if(user.getPassword().equals(password)) {
				return user;
			}
			else
				return null;
		}
		else {
			return null;
    }
  }

	public List<ApplicationUser> getUsers() {
		return userRepository.findAll();
	}

	public ApplicationUser editProfile(String currentUserName, String firstName, String lastName, String username,
			String email, String firstName2, String lastName2, String username2, String email2, String role,
			boolean parseBoolean, boolean parseBoolean2) {
		return null;
	}
}
