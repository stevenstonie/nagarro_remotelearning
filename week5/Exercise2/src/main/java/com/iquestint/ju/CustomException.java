package com.iquestint.ju;

public class CustomException extends RuntimeException {
	public CustomException(String message) {
		super(message);
		throw this;
	}
}