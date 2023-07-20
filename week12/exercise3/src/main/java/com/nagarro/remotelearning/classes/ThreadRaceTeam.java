package com.nagarro.remotelearning.classes;

import java.util.concurrent.CountDownLatch;

public class ThreadRaceTeam implements Runnable {
	private String name;
	private Thread[] members;
	ThreadRaceContext context;
	CountDownLatch startSignal;
	int finishCount;
	int durationOfEachFinished;

	public ThreadRaceTeam(String name, Thread[] members, ThreadRaceContext context, CountDownLatch startSignal) {
		this.name = name;
		this.members = members;
		this.context = context;
		this.startSignal = startSignal;
		finishCount = 0;
		durationOfEachFinished = 0;
	}

	public void reportFinish(int id, long duration) {
		durationOfEachFinished += duration;

		finishCount++;

		if (finishCount == members.length) {
			context.reportTeamFinish(this, duration);
		}
	}

	public String getName() {
		return name;
	}

	public Thread[] getMembers() {
		return members;
	}

	@Override
	public void run() {
		try {
			startSignal.await();

			for (int i = 0; i < members.length; i++) {
				members[i] = new Thread(new ThreadRaceCompetitor(i, this));

				members[i].start();
			}

			for (Thread member : members) {
				member.join();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
