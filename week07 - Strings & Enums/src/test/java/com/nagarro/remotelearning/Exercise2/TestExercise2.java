package com.nagarro.remotelearning.Exercise2;

import com.nagarro.remotelearning.Exercise2.classes.Ex2Solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestExercise2 {
	@DisplayName("Should print the given date in the correct readable form")
	@ParameterizedTest(name = "{index} => date = {0}, dateBeautified = {1}")
	@MethodSource("correctDates")
	public void testValidDates(LocalDate date, String dateBeautified) {
		assertEquals("the date is " + dateBeautified, Ex2Solutions.computeDescriptionFromDate(date));
	}

	@DisplayName("Should not print 12nd / 13rd, or the wrong day of the week")
	@ParameterizedTest(name = "{index} => date = {0}, dateBeautified = {1}")
	@MethodSource("incorrectDates")
	public void testNonValidDates(LocalDate date, String dateBeautified) {
		assertNotEquals("the date is " + dateBeautified, Ex2Solutions.computeDescriptionFromDate(date));
	}

	@Test
	public void test2020_13_13() {
		assertThrows(java.time.DateTimeException.class, () -> LocalDate.of(2020, 13, 13));
	}

	private static Stream<Arguments> correctDates() {
		return Stream.of(
				Arguments.of(LocalDate.of(1929, 01, 15), "Tuesday, 15th of January 1929"),
				Arguments.of(LocalDate.of(2020, 12, 31), "Thursday, 31st of December 2020"),
				Arguments.of(LocalDate.of(2023, 04, 12), "Wednesday, 12th of April 2023"));
	}

	private static Stream<Arguments> incorrectDates() {
		return Stream.of(
				Arguments.of(LocalDate.of(2020, 03, 12), "Thursday, 12nd of March 2020"),
				Arguments.of(LocalDate.of(2020, 03, 13), "Friday, 13rd of March 2020"),
				Arguments.of(LocalDate.of(2020, 03, 13), "Monday, 13th of March 2020"));
	}
}
