package com.nagarro.remotelearning.classes;

// @LoggerAnnotation(string = "SimpleCalculator")
public interface SimpleCalculator {

	@LoggerAnnotation(string = "add")
	public int add(int a, int b);

	// @LoggerAnnotation(string = "substract")
	public int substract(int a, int b);

	@LoggerAnnotation(string = "multiply")
	public int multiply(int a, int b);

	@LoggerAnnotation(string = "divide")
	public int divide(int a, int b);
}
