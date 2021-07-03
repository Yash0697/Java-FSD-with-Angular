package com.fresco.healthcare.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fresco.healthcare.model.ApplicationUser;
import com.fresco.healthcare.model.Response;
import com.fresco.healthcare.repository.ApplicationUserRepository;
import com.fresco.healthcare.security.JwtUtil;
import com.fresco.healthcare.service.ApplicationUserService;
import com.fresco.healthcare.service.UserAuthService;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/")
public class ApplicationUserController {
	
	@Autowired
	ApplicationUserService userService;
	
	@Autowired
	private AuthenticationManager authManager ;
	
	@Autowired
  ApplicationUserRepository userRepository;
  
  @Autowired
  UserAuthService userDetailsService;
	
	@Autowired 
	JwtUtil jwtUtil;
	
	
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody ApplicationUser applicationUser) {
		ApplicationUser newUser = userService.register( applicationUser );
		if(newUser != null)
			return new ResponseEntity<>("Registration Successfull", HttpStatus.OK);
		else
			return new ResponseEntity<>("Password or Username policy failed", HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping(value = "/signin",  headers = "Accept=application/json", produces ="application/json")
	public ResponseEntity<Response> signin(@RequestBody ApplicationUser user) throws Exception {
			authenticate(user.getUsername(), user.getPassword());
			ApplicationUser loginUser = userService.validateLogin(user.getUsername(), user.getPassword());
      UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
      String token = jwtUtil.generateToken(userDetails);
      if(loginUser != null ) {
				Response responseBody = new Response("Authentication Successful!", token, loginUser.getUsername());
				return new ResponseEntity<>( responseBody, HttpStatus.OK);
			}
			else {
				Response responseBody = new Response("Username or Password is Incorrect.", token, loginUser.getUsername());
				return new ResponseEntity<Response>(responseBody,HttpStatus.OK);
			}
		}
	
	@GetMapping("/editprofile/{userId}")
	public ResponseEntity<ApplicationUser> updateUser(@PathVariable("userId") String userId, @RequestParam("currentUserName") String currentUserName,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("username") String username,
			@RequestParam("email") String email,
			@RequestParam("role") String role,
			@RequestParam("isActive") String isActive,
			@RequestParam("isAccountNotLocked") String isAccountNotLocked
			) throws IOException {
		ApplicationUser updatedUser = userService.editProfile(currentUserName, firstName, lastName, username, 
				email, firstName, lastName, username, email, role, Boolean.parseBoolean(isAccountNotLocked), 
				Boolean.parseBoolean(isActive));
		return new ResponseEntity<ApplicationUser>(updatedUser, HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<ApplicationUser>> getAllUser() {
		List<ApplicationUser> applicationUsers = userService.getUsers();
		return new ResponseEntity<List<ApplicationUser>>(applicationUsers,HttpStatus.OK);
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}catch(BadCredentialsException e) {
			throw new Exception("Username or password is incorrect");
		}
		
	}

	

}
