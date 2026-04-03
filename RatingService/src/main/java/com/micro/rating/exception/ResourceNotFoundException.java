package com.micro.rating.exception;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException() {
		super("This resource is not found!!!");
	}
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
