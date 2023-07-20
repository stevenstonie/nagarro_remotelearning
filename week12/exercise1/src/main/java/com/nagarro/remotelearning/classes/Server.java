package com.nagarro.remotelearning.classes;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.Queue;

public class Server implements Runnable {
	private final int MAX_QUEUE_CAPACITY;
	private Queue<String> queue;
	private Queue<String> logRepo;
	private final Object lock;
	RandomAccessFile consoleOutputsFile;

	public Server() {
		MAX_QUEUE_CAPACITY = 10;
		queue = new LinkedList<>();
		logRepo = new LinkedList<>();
		lock = new Object();
		createEmptyConsoleOutputsFile();
	}

	public void addLog(String log) {
		checkIfQueueIsFull();

		addLogToQueue(log);
	}

	public Queue<String> getLogRepo() {
		return logRepo;
	}

	@Override
	public void run() {
		while (true) {
			consumeLog();
		}
	}

	private void createEmptyConsoleOutputsFile() {
		try {
			new File("console_outputs.txt").createNewFile();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		try {
			consoleOutputsFile = new RandomAccessFile("console_outputs.txt", "rw");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void consumeLog() {
		checkIfQueueIsEmpty();

		consumeLogFromQueue();
	}

	private void checkIfQueueIsFull() {
		if (queue.size() == MAX_QUEUE_CAPACITY) {
			synchronized (lock) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	private void addLogToQueue(String log) {
		synchronized (this) { // everytime a common resource is modified, the access of other threads must be blocked
			queue.add(log);
			try {
				consoleOutputsFile.writeBytes("added: " + log + "(current size: " + queue.size() + ")\n");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			notifyAll();
		}
	}

	private void checkIfQueueIsEmpty() {
		if (queue.size() == 0) {
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	private void consumeLogFromQueue() {
		synchronized (this) {
			logRepo.add(queue.poll());

			try {
				consoleOutputsFile.writeBytes("consumed: " + ((LinkedList<String>) logRepo).getLast()
						+ "(current size: " + queue.size() + ")\n");

				notify();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}