package com.fresco.healthcare.security;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;



@Configuration
@EnableWebSecurity
public class ApiAuthenticationEntryPoint extends Http403ForbiddenEntryPoint{
	
	public static final String FORBIDDEN_MESSAGE = "You need to login to access this page";

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException {
//		Response httpResponse = new Response(HttpStatus.FORBIDDEN.value(),
//				HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.getReasonPhrase().toUpperCase(), FORBIDDEN_MESSAGE);
		
		response.setContentType(APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.FORBIDDEN.value());
		OutputStream outStream = response.getOutputStream();
		//ObjectMapper mapper = new ObjectMapper();
//		mapper.writeValue( outStream, httpResponse);
		outStream.flush();
	}
	
}
