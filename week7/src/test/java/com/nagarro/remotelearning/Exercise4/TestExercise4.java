package com.nagarro.remotelearning.Exercise4;

import com.nagarro.remotelearning.Exercise4.classes.Ex4Solution;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

public class TestExercise4 {
	private ByteArrayOutputStream newOutputStream;
	private PrintStream tempSystemOut;
	private PrintStream origSystemOut;

	@BeforeEach
	public void temporaryUseNewOutputStream() {
		newOutputStream = new ByteArrayOutputStream();
		tempSystemOut = new PrintStream(newOutputStream);
		origSystemOut = System.out;
		System.setOut(tempSystemOut);

		Ex4Solution.printRandomGeneratedSentences();

		reAddOrigOutputStream();
	}

	@Test
	public void checkFor20Sentences() {
		int nbOfSentences = 0;

		try (Scanner scanner = new Scanner(newOutputStream.toString())) {
			List<String> linkingSentences = Arrays.asList(Ex4Solution.getLinkingSentences());

			while (scanner.hasNextLine()) {
				if (!linkingSentences.contains(scanner.nextLine())) {
					nbOfSentences++;
				}
			}
		}

		assertEquals(20, nbOfSentences);
	}

	@Test
	public void checkForStraySentences() {
		List<String> linkingSentences = Arrays.asList(Ex4Solution.getLinkingSentences());
		List<String> allWords = initializeAllWords();

		try (Scanner scanner = new Scanner(newOutputStream.toString())) {
			while (scanner.hasNextLine()) {
				String[] currentWords = scanner.nextLine().split(" ");
				checkWordsInSentence(currentWords, allWords, linkingSentences);
			}
		}
	}

	private void checkWordsInSentence(String[] words, List<String> allWords, List<String> linkingSentences) {
		for (String word : words) {
			word = word.replaceAll("\\p{Punct}", "").toLowerCase();

			try {
				assertTrue(allWords.contains(word));
			} catch (AssertionFailedError e) {
				assertTrue(linkingSentences.contains(String.join(" ", words)));
			}
		}
	}

	private List<String> initializeAllWords() {
		List<String> allWords = new ArrayList<>();

		allWords.addAll(Arrays.asList(Ex4Solution.getArticles()));
		allWords.addAll(Arrays.asList(Ex4Solution.getNouns()));
		allWords.addAll(Arrays.asList(Ex4Solution.getPreposition()));
		allWords.addAll(Arrays.asList(Ex4Solution.getVerbs()));

		return allWords;
	}

	private void reAddOrigOutputStream() {
		System.out.flush();
		System.setOut(origSystemOut);
	}
}
