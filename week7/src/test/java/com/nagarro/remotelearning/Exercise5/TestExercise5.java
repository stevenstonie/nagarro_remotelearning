package com.nagarro.remotelearning.Exercise5;

import com.nagarro.remotelearning.Exercise5.classes.Ex5Solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestExercise5 {
	@Test
	public void testPigLatinizedText() {
		String text = "Hello, World!";

		text = Ex5Solutions.removePunctuationsFromString(text);

		assertEquals("elloHay orldWay", Ex5Solutions.returnPigLatinizedText(text));
	}

	@Test
	public void testOneCharacterWord() {
		assertEquals("a", Ex5Solutions.returnPigLatinizedText("a"));
	}

	@Test
	public void testTwoCharactersWord() {
		assertEquals("yaay", Ex5Solutions.returnPigLatinizedText("ay"));
	}
}
