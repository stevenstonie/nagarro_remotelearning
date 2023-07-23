package com.nagarro.remotelearning;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.nagarro.remotelearning.classes.Tools;
import com.nagarro.remotelearning.classes.Train;

public class App {
	public static void main(String[] args) {
		Random rand = new Random();
		Map<Train, List<Integer>> trainMap = new HashMap<>();

		for (int i = 0; i < 10000; i++) {
			Train train = new Train(i, "Type" + rand.nextInt(10), rand.nextInt(19) + 1);

			trainMap.put(train, Tools.addRandomRunningDays(rand));
		}

		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Tools.test(trainMap, rand);
	}
	/* usual times for the hashcode override in this exact order:
	Time taken to retrieve running days for the first train: 273300 nanoseconds
	Time taken to retrieve running days for the first train: 614000 nanoseconds
	Time taken to retrieve running days for the first train: 36500 nanoseconds
	Time taken to retrieve running days for the first train: 391000 nanoseconds
	Time taken to retrieve running days for the first train: 260100 nanoseconds
	Time taken to retrieve running days for the first train: 52700 nanoseconds
	Time taken to retrieve running days for the first train: 399300 nanoseconds
	Time taken to retrieve running days for the first train: 253300 nanoseconds
	Time taken to retrieve running days for the first train: 176300 nanoseconds
	Time taken to retrieve running days for the first train: 3500 nanoseconds
	 */

	/* usual times for the original hashcode in this exact order:
	Time taken to retrieve running days for the first train: 25200 nanoseconds
	Time taken to retrieve running days for the first train: 4200 nanoseconds
	Time taken to retrieve running days for the first train: 6000 nanoseconds
	Time taken to retrieve running days for the first train: 4800 nanoseconds
	Time taken to retrieve running days for the first train: 4000 nanoseconds
	Time taken to retrieve running days for the first train: 3800 nanoseconds
	Time taken to retrieve running days for the first train: 3600 nanoseconds
	Time taken to retrieve running days for the first train: 3700 nanoseconds
	Time taken to retrieve running days for the first train: 5500 nanoseconds
	Time taken to retrieve running days for the first train: 4100 nanoseconds
	 */
}