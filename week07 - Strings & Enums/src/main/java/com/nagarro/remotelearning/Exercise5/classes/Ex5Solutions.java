package com.nagarro.remotelearning.Exercise5.classes;

import java.util.StringTokenizer;

public class Ex5Solutions {
	public static String removePunctuationsFromString(String string) {
		return string.replaceAll("[,.:;!?]", "");
	}

	private static String pigLatinizeWord(String word) {
		if (word.length() == 1) {
			return word;
		}
		final String AY = "ay";

		char firstLetter = word.charAt(0);
		String restOfWord = word.substring(1);
		return restOfWord + firstLetter + AY;
	}

	public static void printPigLatinizedText(String text) {
		System.out.println(returnPigLatinizedText(text));
	}

	public static String returnPigLatinizedText(String text) {
		StringTokenizer tokens = new StringTokenizer(text);
		String pigLatinizedWord = "";
		while (tokens.hasMoreTokens()) {
			pigLatinizedWord += pigLatinizeWord(tokens.nextToken()) + " ";
		}
		return pigLatinizedWord.trim();
	}
}
