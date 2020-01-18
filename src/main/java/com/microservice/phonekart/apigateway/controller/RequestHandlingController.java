package com.microservice.phonekart.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.phonekart.apigateway.model.JwtResponse;

@RestController
@CrossOrigin
public class RequestHandlingController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ResponseEntity<?> firstPage() {

		JwtResponse response = new JwtResponse("any");
		response.setMessage("HELLLLLLLOW WORLDDDDDDDD");

		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = "/carts", method = RequestMethod.GET)
	public ResponseEntity<?> callCartService() {

		String url = "http://localhost:8100/mycart/getTotalItems";

		JwtResponse response = new JwtResponse("any");
		response.setMessage("HELLLLLLLOW WORLDDDDDDDD");

		
		String emp = restTemplate.getForObject(url, String.class);
		response.setError(emp);
		
		
		return ResponseEntity.ok(response);
	}

}
