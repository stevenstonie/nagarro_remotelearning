package com.nagarro.remotelearning;

import java.util.stream.IntStream;

public class Utils {
	public static boolean isPalindrome(int number) {
		String stringNumber = String.valueOf(number);
		return stringNumber.equals(new StringBuilder(stringNumber).reverse().toString());
	}

	public static long countEvenPalindromes(int start, int end) {
		return IntStream.rangeClosed(start, end)
				.filter(Utils::isPalindrome)
				.filter(n -> n % 2 == 0)
				.count();
	}

	public static long countOddPalindromes(int start, int end) {
		return IntStream.rangeClosed(start, end)
				.filter(Utils::isPalindrome)
				.filter(n -> n % 2 != 0)
				.count();
	}

	public static long countPalindromes(int start, int end) {
		return IntStream.rangeClosed(start, end)
				.filter(Utils::isPalindrome)
				.count();
	}
}
