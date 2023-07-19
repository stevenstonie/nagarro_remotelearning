package com.nagarro.remotelearning.exceptions;

public class MaxCapacityReachedException extends RuntimeException {
	public MaxCapacityReachedException(String message) {
		super(message);
	}
}