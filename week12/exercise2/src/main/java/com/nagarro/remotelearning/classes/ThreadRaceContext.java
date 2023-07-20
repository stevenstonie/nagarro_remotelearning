package com.nagarro.remotelearning.classes;

import java.util.ArrayList;
import java.util.List;

public class ThreadRaceContext {
	private List<Pair<Integer, Long>> thoseWhoFinished = new ArrayList<>();

	public synchronized void reportFinish(int competitorId, long duration) {
		System.out.println("finish line crossed");

		thoseWhoFinished.add(new Pair<>(competitorId, duration));
	}

	public void listFinalRankings(Thread[] competitors) {
		System.out.println("Final rankings:");
		for (int i = 0; i < thoseWhoFinished.size(); i++) {
			System.out.println(i + 1 + ". competitor " + thoseWhoFinished.get(i).getFirst()
					+ " (time: " + thoseWhoFinished.get(i).getSecond() + "ms)");
		}
	}
}