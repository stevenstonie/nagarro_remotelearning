package com.nagarro.remotelearning.Exercise3.classes;

import java.util.Arrays;

public class Ex3Solutions {
	public static String titlelize(String text) {
		String[] words = text.toLowerCase().split(" ");
		String[] linkingWords = { "the", "a", "an", "to", "in", "of" };

		Boolean nextWordIsFirstWord = true;
		for (int indexOfWord = 0; indexOfWord < words.length; indexOfWord++) {
			words[indexOfWord] = checkIfNeedsCapitalization(words[indexOfWord], nextWordIsFirstWord, linkingWords);

			nextWordIsFirstWord = checkForPunctuation(words[indexOfWord]);
		}

		return String.join(" ", words);
	}

	private static String checkIfNeedsCapitalization(String word, Boolean nextWordIsFirstWord, String[] linkingWords) {
		// if the word is at the beginning of the sentence dont search for it in the list; capitalize it.
		if (nextWordIsFirstWord) {
			nextWordIsFirstWord = false;
			word = capitalizeFirstLetter(word);
		} else {
			// replaceFirst("[^a-zA-Z]", "") removes all non-alphabets from the word
			if (!Arrays.asList(linkingWords).contains(word.replaceFirst("[^a-zA-Z]", ""))) {
				word = capitalizeFirstLetter(word);
			}
		}

		return word;
	}

	private static Boolean checkForPunctuation(String string) {
		return string.charAt(string.length() - 1) == '.'
				|| string.charAt(string.length() - 1) == '!'
				|| string.charAt(string.length() - 1) == '?';
	}

	private static String capitalizeFirstLetter(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
	}
}
