package com.microservice.phonekart.apigateway.microService_model;

public class Factory {

	private Factory() {
		
	}
	
	public static synchronized Object create(Class c) {
		
		Object o = null;
		try {
			
			//Class cls = Class.forName(c);
			return c.newInstance();
			
		}catch(Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
}
