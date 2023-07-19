package com.nagarro.remotelearning;

import com.nagarro.remotelearning.classes.Logger;
import com.nagarro.remotelearning.classes.MyInvocationHandler;
import com.nagarro.remotelearning.classes.SimpleCalculator;
import com.nagarro.remotelearning.classes.SimpleCalculatorImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Proxy;
import java.util.Scanner;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class AppTest {
	ByteArrayOutputStream newOutputStream;
	PrintStream tempSystemOut;
	PrintStream origSystemOut;
	SimpleCalculator proxiedCalculator;

	@BeforeEach
	public void setup() {
		temporarilyUseNewOutputStream();

		initializeProxiedCalculator();
	}

	@AfterEach
	public void reAddOrigOutputStream() {
		System.out.flush();
		System.setOut(origSystemOut);
	}

	@DisplayName("Should test the output for all operations")
	@ParameterizedTest(name = "{index} => functionName = {0}, functionArgs = {1}, output = {2}")
	@MethodSource("operationsInputsOutputs")
	public void testOutputForAllOperations(String functionName, String functionArgs, String output) {
		String[] args = functionArgs.split(",");
		int arg1 = Integer.parseInt(args[0].trim());
		int arg2 = Integer.parseInt(args[1].trim());
		int result = returnOperationResult(functionName, arg1, arg2);

		try (Scanner scanner = new Scanner(newOutputStream.toString())) {
			assertEquals("Object type: com.nagarro.remotelearning.classes.SimpleCalculatorImpl", scanner.nextLine());
			assertEquals("Method name: " + functionName, scanner.nextLine());
			assertEquals("Method arguments: " + functionArgs, scanner.nextLine());
			assertEquals("Returned value: " + output, scanner.nextLine());
			assertEquals(Integer.parseInt(output), result); // check for the operation itself
		}
	}

	private static Stream<Arguments> operationsInputsOutputs() {
		return Stream.of(
				Arguments.of("add", "1, 2", "3"),
				Arguments.of("substract", "4, 7", "-3"),
				Arguments.of("multiply", "3, 3", "9"),
				Arguments.of("divide", "9, 4", "2"));
	}

	private int returnOperationResult(String string, int arg1, int arg2) {
		int result = 0;
		switch (string) {
			case "add":
				result = proxiedCalculator.add(arg1, arg2);
				break;
			case "substract":
				result = proxiedCalculator.substract(arg1, arg2);
				break;
			case "multiply":
				result = proxiedCalculator.multiply(arg1, arg2);
				break;
			case "divide":
				result = proxiedCalculator.divide(arg1, arg2);
				break;
		}
		return result;
	}

	private void temporarilyUseNewOutputStream() {
		newOutputStream = new ByteArrayOutputStream();
		tempSystemOut = new PrintStream(newOutputStream);
		origSystemOut = System.out;
		System.setOut(tempSystemOut);
	}

	private void initializeProxiedCalculator() {
		ClassLoader classLoader = Logger.class.getClassLoader();
		SimpleCalculatorImpl calculator = new SimpleCalculatorImpl();
		MyInvocationHandler handler = new MyInvocationHandler(calculator);

		proxiedCalculator = (SimpleCalculator) Proxy.newProxyInstance(
				classLoader, new Class[] { SimpleCalculator.class }, handler);
	}
}
