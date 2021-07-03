package com.fresco.healthcare.service;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fresco.healthcare.model.ApplicationUser;
import com.fresco.healthcare.repository.ApplicationUserRepository;


@Service
public class UserAuthService implements UserDetailsService{
	
	public static final String USER_NOT_FOUND_FOR_USERNAME = "User not found for username ";
	
	@Autowired
	ApplicationUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException(USER_NOT_FOUND_FOR_USERNAME + username);
		}
		else {
			userRepository.save(user);
			return new User(user.getUsername(), user.getPassword(), (Collection<? extends GrantedAuthority>) new ArrayList<GrantedAuthority>());
		}
	}
	
}
