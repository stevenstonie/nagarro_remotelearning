package com.nagarro.remotelearning;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.nagarro.remotelearning.classes.Combiner;
import com.nagarro.remotelearning.classes.TransformOperations;

public class TransformOperationsTest {
	private final ByteArrayOutputStream tempOut = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final String endline = "\r\n";

	@BeforeEach
	public void setUpStream() {
		System.setOut(new PrintStream(tempOut));
	}

	@AfterEach
	public void restoreStream() {
		tempOut.reset();
		System.setOut(originalOut);
	}

	@ParameterizedTest(name = "{index} => numbers = {0}, expected = {1}")
	@MethodSource("sumOfIntegersArgs")
	public void testSumOfIntegers(Integer[] numbers, Integer expected) {
		Combiner<Integer> summer = (a, b, c, d) -> a + b + c + d;

		TransformOperations.applyTransformations(numbers, summer, System.out::println);

		assertEquals(expected + endline, tempOut.toString());
	}

	@ParameterizedTest(name = "{index} => numbers = {0}, expected = {1}")
	@MethodSource("productOfIntegersArgs")
	public void testProductOfIntegers(Integer[] numbers, Integer expected) {
		Combiner<Integer> multiplier = (a, b, c, d) -> a * b * c * d;

		TransformOperations.applyTransformations(numbers, multiplier, System.out::println);

		assertEquals(expected + endline, tempOut.toString());
	}

	@ParameterizedTest(name = "{index} => numbers = {0}, expected = {1}")
	@MethodSource("stringConcatArgs")
	public void testStringConcat(String[] strings, String expected) {
		Combiner<String> stringConcat = (a, b, c, d) -> a + b + c + d;

		TransformOperations.applyTransformations(strings, stringConcat, System.out::println);

		assertEquals(expected + endline, tempOut.toString());
	}

	@ParameterizedTest(name = "{index} => numbers = {0}, expected = {1}")
	@MethodSource("stringConcatWithSpacesArgs")
	public void testStringConcatWithSpaces(String[] strings, String expected) {
		Combiner<String> stringConcat = (a, b, c, d) -> a + " " + b + " " + c + " " + d;

		TransformOperations.applyTransformations(strings, stringConcat, System.out::println);

		assertEquals(expected + endline, tempOut.toString());
	}

	@Test
	public void testLimitOf4Arguments() {
		Integer[] numbers = {1, 2, 3};
		Combiner<Integer> summer = (a, b, c, d) -> a + b + c + d;

		assertThrows(IllegalArgumentException.class,
				() -> TransformOperations.applyTransformations(numbers, summer, System.out::println));
	}

	private static Stream<Arguments> stringConcatWithSpacesArgs() {
		return Stream.of(
				Arguments.of(new String[]{"a", "b", "c", "d"}, "a b c d"),
				Arguments.of(new String[]{"a", "b", "c", "d", "e"}, "a b c d"),
				Arguments.of(new String[]{"ben", "ate a", "cookie,", "damn.."}, "ben ate a cookie, damn.."));
	}

	private static Stream<Arguments> stringConcatArgs() {
		return Stream.of(
				Arguments.of(new String[]{"a", "b", "c", "d"}, "abcd"),
				Arguments.of(new String[]{"a", "b", "c", "d", "e"}, "abcd"),
				Arguments.of(new String[]{"aha", "ben", "cookie", "damn"}, "ahabencookiedamn"));
	}

	private static Stream<Arguments> productOfIntegersArgs() {
		return Stream.of(
				Arguments.of(new Integer[]{1, 2, 3, 4}, 24),
				Arguments.of(new Integer[]{-1, -1, 4, 5}, 20),
				Arguments.of(new Integer[]{0, -1, 0, 0}, 0));
	}

	private static Stream<Arguments> sumOfIntegersArgs() {
		return Stream.of(
				Arguments.of(new Integer[]{1, 2, 3, 4}, 10),
				Arguments.of(new Integer[]{2, 3, 4, 5, 200}, 14),
				Arguments.of(new Integer[]{0, -1, 0, 0}, -1));
	}
}