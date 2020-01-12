package com.microservice.phonekart.apigateway.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microservice.phonekart.apigateway.model.JWTRequestForSignUP;
import com.microservice.phonekart.apigateway.repository.MySQLRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	MySQLRepository mysqlRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username.equals(username)) {
			return new User(username, "test", new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}


	public boolean checkIfUserAuthorised(String username,String password)  {
		return (mysqlRepo.getSignedInUser(username,password));
	}
	
	public boolean checkIfUserExist(String username)  {
		return(mysqlRepo.getUser(username)); 
	}

	public void createNewRecord(JWTRequestForSignUP user) {

		mysqlRepo.createNewRecord(user);
		
	}

}
