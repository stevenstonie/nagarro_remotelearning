package com.nagarro.remotelearning.classes;

import java.util.concurrent.CountDownLatch;

public class Utils {
	public static void startRace(CountDownLatch startSignal) {
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

	public static void createTeams(Thread[] teams, ThreadRaceContext context, CountDownLatch startSignal) {
		for (int i = 0; i < 10; i++) {
			String teamName = "team" + (i + 1);

			teams[i] = new Thread(new ThreadRaceTeam(teamName, new Thread[4], context, startSignal));
			teams[i].start();
		}
	}

	public static void joinAllTeams(Thread[] teams) {
		for (Thread team : teams) {
			try {
				team.join();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage() + " in method " + e.getStackTrace()[0].getMethodName());
			}
		}
		// TODO: do i really have to use join() on the members of each team as well?
	}
}
