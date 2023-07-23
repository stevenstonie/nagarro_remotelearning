package com.nagarro.remotelearning.exercise2;

import java.util.Arrays;
import java.util.Collection;

public class Ex2Solution {
	public static <T extends Comparable<T>> void bubbleSort(Collection<T> givenCollection) {
		T[] arr = givenCollection.toArray((T[]) new Comparable[givenCollection.size()]);
		boolean swapped;

		for (int i = 0; i < arr.length - 1; i++) {
			swapped = false;

			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j].compareTo(arr[j + 1]) > 0) {
					swap(j, j + 1, arr);
					swapped = true;
				}
			}

			if (!swapped) {
				break;
			}
		}

		givenCollection.clear();
		givenCollection.addAll(Arrays.asList(arr));
	}

	private static <T> void swap(int i, int j, T[] arr) {
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
