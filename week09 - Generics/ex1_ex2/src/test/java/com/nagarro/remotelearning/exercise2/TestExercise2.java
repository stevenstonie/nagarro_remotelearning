package com.nagarro.remotelearning.exercise2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.junit.jupiter.api.Test;

public class TestExercise2 {
	@Test
	public void testSortFunctionWithList() {
		List<Integer> listOfNumbers = new ArrayList<>(Arrays.asList(1, 2, 0, 4, 1, 3));
		Ex2Solution.bubbleSort(listOfNumbers);
		assertEquals(Arrays.asList(0, 1, 1, 2, 3, 4), listOfNumbers);
	}

	@Test
	public void testSortFunctionWithVector() {
		Vector<String> vectorOfFruits = new Vector<>(Arrays.asList("mar", "para", "portocala", "gutuie", "bormasina"));
		Ex2Solution.bubbleSort(vectorOfFruits);
		assertEquals(Arrays.asList("bormasina", "gutuie", "mar", "para", "portocala"), vectorOfFruits);
	}

	@Test
	public void testSortFunctionWithLinkedHashSet() {
		Set<String> linkedSetOfThings = new LinkedHashSet<>();
		List<String> listOfThings = new ArrayList<>(Arrays.asList("nail", "fox", "school", "rainbow", "roof"));

		Ex2Solution.bubbleSort(listOfThings);
		linkedSetOfThings.addAll(listOfThings);

		assertEquals(listOfThings, new ArrayList<>(linkedSetOfThings));
	}
}
