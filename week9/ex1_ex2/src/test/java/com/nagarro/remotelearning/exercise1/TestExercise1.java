package com.nagarro.remotelearning.exercise1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TestExercise1 {
	@Test
	public void testCollectionWithIntegers() {
		MyCollectionImpl<Integer> myIntCollection = new MyCollectionImpl<>();
		List<Integer> intList = new ArrayList<>();
		intList.add(1);
		intList.add(2);
		intList.add(0);
		intList.add(4);
		myIntCollection.addAll(intList);

		assertTrue(myIntCollection.containsAll(intList));
	}

	@Test
	public void testCollectionWithStrings() {
		MyCollectionImpl<String> myStringCollection = new MyCollectionImpl<>();
		List<String> stringList = new ArrayList<>();
		stringList.add("cool");
		stringList.add("collection");
		stringList.add("bro");
		myStringCollection.addAll(stringList);

		assertTrue(myStringCollection.containsAll(stringList));
	}
}
