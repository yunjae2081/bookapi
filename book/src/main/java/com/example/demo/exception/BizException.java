package com.example.demo.exception;

@SuppressWarnings("serial")
public class BizException extends RuntimeException{

	String message;
	
	public BizException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
