package com.nagarro.remotelearning.classes;

import java.util.concurrent.CountDownLatch;

public class ThreadRaceCompetitor implements Runnable {
	private int id;
	private ThreadRaceContext context;
	private CountDownLatch startSignal;

	public ThreadRaceCompetitor(int id, ThreadRaceContext context, CountDownLatch startSignal) {
		this.id = id;
		this.context = context;
		this.startSignal = startSignal;
	}

	@Override
	public void run() {
		try {
			startSignal.await();

			long duration = (long) (Math.random() * 4000 + 1000);
			Thread.sleep(duration);

			context.reportFinish(id, duration);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}