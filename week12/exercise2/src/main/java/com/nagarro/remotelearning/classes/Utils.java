package com.nagarro.remotelearning.classes;

import java.util.concurrent.CountDownLatch;

public class Utils {
	public static void setCompetitors(Thread[] competitors, ThreadRaceContext context, CountDownLatch startSignal) {
		for (int i = 0; i < 10; i++) {
			competitors[i] = new Thread(new ThreadRaceCompetitor(i + 1, context, startSignal));
			competitors[i].start();
		}
	}

	public static void startCountdown(CountDownLatch startSignal) {
		for (int i = 3; i >= 1; i--) {
			try {
				System.out.print(i + " ");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}

		System.out.println("start!");
		startSignal.countDown();
	}

	public static void joinCompetitors(Thread[] competitors) {
		for (Thread competitor : competitors) {
			try {
				competitor.join();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
