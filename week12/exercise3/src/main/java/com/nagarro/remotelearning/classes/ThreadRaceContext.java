package com.nagarro.remotelearning.classes;

import java.util.ArrayList;
import java.util.List;

public class ThreadRaceContext {
	private List<Pair<String, Long>> teamsThatFinished;

	public ThreadRaceContext() {
		teamsThatFinished = new ArrayList<>();
	}

	public synchronized void reportTeamFinish(ThreadRaceTeam team, long duration) {
		System.out.println("a team has finished. ");

		teamsThatFinished.add(new Pair<String, Long>(team.getName(), duration));
	}

	public void listFinalRankings(Thread[] teams) {
		System.out.println("the final rankings are as follows: ");
		for (int i = 0; i < teamsThatFinished.size(); i++) {
			System.out.println((i + 1) + ". "
					+ teamsThatFinished.get(i).getFirst()
					+ "(duration of last member: " + teamsThatFinished.get(i).getDurationOfLastFinisher() + "ms)");
		}
	}
}