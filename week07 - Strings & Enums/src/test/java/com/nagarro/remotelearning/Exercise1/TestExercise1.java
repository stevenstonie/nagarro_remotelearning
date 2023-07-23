package com.nagarro.remotelearning.Exercise1;

import com.nagarro.remotelearning.Exercise1.classes.RegexRelatedMethods;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestExercise1 {
	@ParameterizedTest
	@ValueSource(strings = { "mmmmm@maybe.yes", "mmmmm@mayb.no" })
	public void testEmailValidationTrue(String input) {
		assertTrue(RegexRelatedMethods.validateEmail(input));
	}

	@ParameterizedTest
	@ValueSource(strings = { "@maybe.yes", "mmmmm@maybe@.yes", "mmmm.yes", "mmmmm@maybe.k", "mmmmm@maybe.yeah" })
	public void testEmailValidationFalse(String input) {
		assertFalse(RegexRelatedMethods.validateEmail(input));
	}

	@ParameterizedTest
	@ValueSource(strings = { "1234567890mM*", "1234567890m*M", "1234567890Mm*", "M*m1234567890", "1234567890*mM",
			"*M1234567890m" })
	public void testPasswordValidationTrue(String input) {
		assertTrue(RegexRelatedMethods.validatePassword(input));
	}

	@ParameterizedTest
	@ValueSource(strings = { "123Mm*", "1234567890M*", "1234567890m*", "M1234567890m", "mM1234567890" })
	public void testPasswordValidationFalse(String input) {
		assertFalse(RegexRelatedMethods.validatePassword(input));
	}

	@ParameterizedTest
	@ValueSource(strings = { "0040771337420", "+40771337420", "0771337420" })
	public void testPhoneNumberValidationTrue(String input) {
		assertTrue(RegexRelatedMethods.validatePhoneNumber(input));
	}

	@ParameterizedTest
	@ValueSource(strings = { "123456789", "+4+123456789", "00+1234567890123", "++1234567890123" })
	public void testPhoneNumberValidationFalse(String input) {
		assertFalse(RegexRelatedMethods.validatePhoneNumber(input));
	}
	// 001234567890 considered true as it verifies [0-9]{10,}
	// +001234567890123 considered true as it verifies [+][0-9]{11,}
}
