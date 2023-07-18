package com.nagarro.remotelearning.Exercise3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.nagarro.remotelearning.Exercise3.classes.Ex3Solutions;

public class TestExercise3 {
	@ParameterizedTest(name = "{index} => text = {0}, capitalizedText = {1}")
	@MethodSource("expectedCapitalization")
	public void testValidDates(String text, String capitalizedText) {
		assertEquals(capitalizedText, Ex3Solutions.titlelize(text));
	}

	private static Stream<Arguments> expectedCapitalization() {
		return Stream.of(
				Arguments.of("today i ate an apple. the apple was pretty sour. here, its a seed from the apple.",
						"Today I Ate an Apple. The Apple Was Pretty Sour. Here, Its a Seed From the Apple."),

				Arguments.of("the quick brown fox jumps over the lazy dog.",
						"The Quick Brown Fox Jumps Over the Lazy Dog."),

				Arguments.of("the,,, the. the! the? the: the; the",
						"The,,, the. The! The? The: the; the"));
	}
}
