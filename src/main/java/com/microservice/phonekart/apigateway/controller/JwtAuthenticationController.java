package com.microservice.phonekart.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.phonekart.apigateway.config.JwtTokenUtil;
import com.microservice.phonekart.apigateway.model.JWTRequestForSignUP;
import com.microservice.phonekart.apigateway.model.JwtRequest;
import com.microservice.phonekart.apigateway.model.JwtResponse;
import com.microservice.phonekart.apigateway.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JWTRequestForSignUP authenticationRequest)
			throws Exception {

		System.out.println("Request aayi to yaha pr HHHHHHHH !!!!!");

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		boolean userExisit = userDetailsService.checkIfUserExist(authenticationRequest.getUsername());

		if (!userExisit) {
			userDetailsService.createNewRecord(authenticationRequest);
		}

		final String token = jwtTokenUtil.generateToken(userDetails);

		JwtResponse response = new JwtResponse(token);

		if (userExisit) {
			response.setError("User Already exsist. Please choose another Username");
		} else {
			response.setMessage("New User Account created succesfully.");
		}

		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationTokenFoRSignIN(@RequestBody JwtRequest authenticationRequest)
			throws Exception {

		System.out.println("Request aayi SIGN IN to yaha pr  !!!!!");

		boolean userExisit = userDetailsService.checkIfUserAuthorised(authenticationRequest.getUsername(),
				authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String token = null;

		JwtResponse response = new JwtResponse(token);

		if (userExisit) {
			response.setMessage("User Login Succesfully");
			token = jwtTokenUtil.generateToken(userDetails);
			response.setToken(token);
		} else {
			response.setError("Please check Your Username and/or password");
			response.setToken(null);
		}
		
		return ResponseEntity.ok(response);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			// authenticationManager.authenticate(new
			// UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
