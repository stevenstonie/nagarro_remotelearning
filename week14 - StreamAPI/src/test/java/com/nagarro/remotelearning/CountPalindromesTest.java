package com.nagarro.remotelearning;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class CountPalindromesTest {
	@Test
	public void testOddPalindromesFrom1000To10000() {
		long oddPalindromeCounter = Utils.countOddPalindromes(1000, 10000);

		assertEquals(50, oddPalindromeCounter);
	}

	@Test
	public void testEvenPalindromesFrom1000To10000() {
		long evenPalindromeCounter = Utils.countEvenPalindromes(1000, 10000);

		assertEquals(40, evenPalindromeCounter);
	}

	@Test
	public void testPalindromesFrom1000To10000() {
		long palindromeCounter = Utils.countPalindromes(1000, 10000);

		assertEquals(Utils.countOddPalindromes(1000, 10000) + Utils.countEvenPalindromes(1000, 10000),
				palindromeCounter);
	}

	@Test
	public void testReverseOrderOfRange() {
		long numberCounter = IntStream.range(10002, 10000)
				.count();

		assertEquals(0, numberCounter);
	}

	@Test
	public void testSingleDigitPalindromes() {
		long palindromeCounter = Utils.countPalindromes(0, 10);

		assertEquals(10, palindromeCounter);
	}

	@Test
	public void testNextEvenPalindromeAfter100() {
		OptionalInt firstPalindrome = IntStream.range(100, 300)
				.filter(Utils::isPalindrome)
				.filter(n -> n % 2 == 0)
				.findFirst();

		assertEquals(202, firstPalindrome.getAsInt());
	}
}