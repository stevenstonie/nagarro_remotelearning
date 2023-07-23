package com.nagarro.remotelearning;

import java.util.concurrent.CountDownLatch;

import com.nagarro.remotelearning.client.Client;
import com.nagarro.remotelearning.server.Server;

public class WebSocketApp {
	public static void main(String... args) {
		CountDownLatch latch = new CountDownLatch(1);

		Thread serverThread = new Thread(new Server(8080, latch));
		serverThread.start();

		try {
			latch.await();
		} catch (InterruptedException e) {
			System.out.println("exception caught on Main.java, latch.await(): " + e.getMessage());
		}

		Thread clientThread = new Thread(new Client());
		clientThread.start();
	}
}
