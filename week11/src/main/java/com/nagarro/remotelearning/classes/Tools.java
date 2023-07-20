package com.nagarro.remotelearning.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Tools {
	public static void test(Map<Train, List<Integer>> trainMap, Random rand) {
		@SuppressWarnings("unchecked") // Type safety: The expression of type List[] needs unchecked conversion to conform to List<Integer>[]
		List<Integer>[] runningDays = new ArrayList[10];

		for (int i = 0; i < 10; i++) {
			long startTime = System.nanoTime();

			runningDays[i] = trainMap.get(new Train(i, "Type" + rand.nextInt(10), rand.nextInt(19) + 1));

			long endTime = System.nanoTime();
			System.out.println("Time taken to retrieve running days for the first train: "
					+ (endTime - startTime) + " nanoseconds");
		}
	}

	public static List<Integer> addRandomRunningDays(Random rand) {
		List<Integer> runningDays = new ArrayList<>();

		int nbOfRunningDays = rand.nextInt(19) + 1;
		for (int j = 0; j < nbOfRunningDays; j++) {
			runningDays.add(rand.nextInt(364) + 1);
		}

		return runningDays;
	}
}