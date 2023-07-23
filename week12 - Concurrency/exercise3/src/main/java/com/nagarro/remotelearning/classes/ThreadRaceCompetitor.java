package com.nagarro.remotelearning.classes;

public class ThreadRaceCompetitor implements Runnable {
	private int id;
	private ThreadRaceTeam team;

	public ThreadRaceCompetitor(int id, ThreadRaceTeam team) {
		this.id = id;
		this.team = team;
	}

	@Override
	public void run() {
		try {
			long duration = (long) (Math.random() * 6000 + 2000);
			Thread.sleep(duration);

			team.reportFinish(id, duration);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}