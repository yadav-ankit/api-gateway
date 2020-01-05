package com.microservice.phonekart.apigateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestHandlingController {

	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}
}
