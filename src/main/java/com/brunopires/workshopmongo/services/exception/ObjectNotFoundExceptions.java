package com.brunopires.workshopmongo.services.exception;

public class ObjectNotFoundExceptions extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundExceptions(String msg) {
		super(msg);
	}

}
